<template>
  <div class="container mt-4">
    <!-- Chọn ngày  -->
    <div class="card mb-4">
      <div
        class="card-header d-flex justify-content-between align-items-center"
      >
        <h4>Doanh Thu Theo Ngày</h4>
        <input
          type="date"
          class="form-control"
          style="width: 200px"
          v-model="selectedDate"
          @change="fetchDailyRevenue"
        />
      </div>
      <div class="card-body">
        <div v-if="dailyRevenue" class="row">
          <div class="col-md-3 mb-3">
            <div class="p-3 bg-primary text-white rounded">
              <h6>Số Hóa Đơn</h6>
              <h4>{{ dailyRevenue.soHoaDon }}</h4>
            </div>
          </div>
          <div class="col-md-3 mb-3">
            <div class="p-3 bg-success text-white rounded">
              <h6>Tổng Doanh Thu</h6>
              <h4>{{ formatCurrency(dailyRevenue.tongDoanhThu) }}</h4>
            </div>
          </div>
          <div class="col-md-3 mb-3">
            <div class="p-3 bg-warning text-white rounded">
              <h6>Tổng Phí Ship</h6>
              <h4>{{ formatCurrency(dailyRevenue.tongPhiShip) }}</h4>
            </div>
          </div>
          <div class="col-md-3 mb-3">
            <div class="p-3 bg-info text-white rounded">
              <h6>Doanh Thu Thực Tế</h6>
              <h4>{{ formatCurrency(dailyRevenue.doanhThuThucTe) }}</h4>
            </div>
          </div>
        </div>
        <div v-if="!dailyRevenue" class="text-center py-4">
          <p class="text-muted">Không có dữ liệu doanh thu cho ngày đã chọn</p>
        </div>
        <canvas
          v-if="dailyRevenue"
          id="dailyRevenueChart"
          height="100"
        ></canvas>
      </div>
    </div>

    <!-- DoanhThuTheoThang -->

    <div class="card mt-4">
      <div
        class="card-header d-flex justify-content-between align-items-center"
      >
        <h4>Doanh Thu Theo Tháng</h4>
        <div class="d-flex gap-2">
          <select
            class="form-select"
            style="width: 100px"
            v-model="selectedMonth"
          >
            <option v-for="month in 12" :key="month" :value="month">
              Tháng {{ month }}
            </option>
          </select>
          <select
            class="form-select"
            style="width: 120px"
            v-model="selectedYear"
          >
            <option v-for="year in years" :key="year" :value="year">
              Năm {{ year }}
            </option>
          </select>
        </div>
      </div>
      <div class="card-body">
        <canvas id="monthlyRevenueChart" height="100"></canvas>
      </div>
    </div>

    <!-- Doanh Thu Theo Nam -->
    <div class="card mt-4">
      <div
        class="card-header d-flex justify-content-between align-items-center"
      >
        <h4>Doanh Thu Theo Năm</h4>
        <select
          class="form-select"
          style="width: 120px"
          v-model="selectedYearRevenue"
        >
          <option v-for="year in years" :key="year" :value="year">
            Năm {{ year }}
          </option>
        </select>
      </div>
      <div class="card-body">
        <canvas id="yearlyRevenueChart" height="100"></canvas>
      </div>
    </div>

    <!-- Doanh Thu Theo Khoang Thoi Gian -->
    <div class="card mt-4">
      <div
        class="card-header d-flex justify-content-between align-items-center"
      >
        <h4>Doanh Thu Theo Khoảng Thời Gian</h4>
        <div class="d-flex gap-2">
          <input
            type="date"
            class="form-control"
            v-model="startDate"
            @change="fetchDateRangeRevenue"
          />
          <input
            type="date"
            class="form-control"
            v-model="endDate"
            @change="fetchDateRangeRevenue"
          />
        </div>
      </div>
      <div class="card-body">
        <div v-if="dateRangeRevenue.length === 0" class="text-center py-4">
          <p class="text-muted">
            Không có dữ liệu cho khoảng thời gian đã chọn
          </p>
        </div>
        <canvas v-else id="dateRangeRevenueChart" height="100"></canvas>
      </div>
    </div>

    <!-- Top 5 sp -->
    <div class="card">
      <div class="card-header">
        <h4>Top 5 Áo Dài Bán Chạy</h4>
      </div>
      <div class="card-body">
        <canvas id="combinedChart" height="200"></canvas>
      </div>
    </div>

    <!-- Thống kê loại áo dài -->
    <div class="card mt-4">
      <div class="card-header">
        <h4>Phân Bố Loại Áo Dài</h4>
      </div>
      <div class="card-body">
        <canvas id="pieChart" height="100"></canvas>
      </div>
    </div>

    <!-- chi tiet san pham duoc chon -->
    <div v-if="selectedProduct" class="mt-4">
      <div class="card">
        <div class="card-header">
          <h5>Chi tiết sản phẩm</h5>
        </div>
        <div class="card-body">
          <div class="row">
            <div class="col-md-6">
              <p><strong>Mã áo dài:</strong> {{ selectedProduct.maAoDai }}</p>
              <p><strong>Tên áo dài:</strong> {{ selectedProduct.tenAoDai }}</p>
            </div>
            <div class="col-md-6">
              <p>
                <strong>Số lượng đã bán:</strong>
                {{ selectedProduct.tongSoLuongDaBan }}
              </p>
              <p>
                <strong>Doanh thu:</strong>
                {{ formatCurrency(selectedProduct.tongTienDaBan) }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Chart from "chart.js/auto";
import ThongKeService from "../../services/ThongKeService";

export default {
  name: "TopProductsChart",
  data() {
    return {
      chart: null,
      dailyRevenueChart: null,
      topProducts: [],
      // trong ngày
      selectedProduct: null,
      dailyRevenue: null,
      selectedDate: new Date().toISOString().split("T")[0],
      // theo tháng
      monthlyRevenueChart: null,
      selectedMonth: new Date().getMonth() + 1,
      selectedYear: new Date().getFullYear(),
      monthlyRevenue: [],
      years: [2023, 2024, 2025],
      // theo năm
      yearlyRevenueChart: null,
      selectedYearRevenue: new Date().getFullYear(),
      yearlyRevenue: [],
      // theo khoảng thời gian
      dateRangeRevenueChart: null,
      dateRangeRevenue: [],
      startDate: new Date().toISOString().split("T")[0],
      endDate: new Date().toISOString().split("T")[0],
      // phân bố loại áo dài
      pieChart: null,
      loaiAoDaiData: [],
    };
  },
  async mounted() {
    await Promise.all([
      this.fetchTopProducts(),
      this.fetchDailyRevenue(),
      this.fetchMonthlyRevenue(),
      this.fetchYearlyRevenue(),
      this.fetchDateRangeRevenue(),
      this.fetchLoaiAoDaiData(),
    ]);
    this.initChart();
    this.initDailyRevenueChart();
    this.initMonthlyRevenueChart();
    this.initYearlyRevenueChart();
    this.initDateRangeRevenueChart();
    this.initPieChart();
  },
  beforeUnmount() {
    if (this.chart) {
      this.chart.destroy();
    }
    if (this.dailyRevenueChart) {
      this.dailyRevenueChart.destroy();
    }
    if (this.monthlyRevenueChart) {
      this.monthlyRevenueChart.destroy();
    }
    if (this.yearlyRevenueChart) {
      this.yearlyRevenueChart.destroy();
    }
    if (this.dateRangeRevenueChart) {
      this.dateRangeRevenueChart.destroy();
    }
    if (this.pieChart) {
      this.pieChart.destroy();
    }
  },
  watch: {
    selectedMonth() {
      this.fetchMonthlyRevenue();
    },
    selectedYear() {
      this.fetchMonthlyRevenue();
    },
    selectedYearRevenue() {
      this.fetchYearlyRevenue();
    },
    loaiAoDaiData() {
      if (this.loaiAoDaiData.length) {
        this.initPieChart();
      }
    },
  },
  methods: {
    formatCurrency(value) {
      return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(value);
    },
    // top 5 sp
    async fetchTopProducts() {
      try {
        const response = await ThongKeService.getTop5SanPham();
        this.topProducts = response.data.body;
        // Sắp xếp dữ liệu theo doanh thu giảm dần
        this.topProducts.sort((a, b) => b.tongTienDaBan - a.tongTienDaBan);
        this.initChart();
      } catch (error) {
        console.error("Error fetching top products:", error);
        this.$toast.error("Không thể tải dữ liệu top sản phẩm");
      }
    },

    handleChartClick(chart, event) {
      const points = chart.getElementsAtEventForMode(
        event,
        "nearest",
        { intersect: true },
        true
      );
      if (points.length) {
        const firstPoint = points[0];
        const productIndex = firstPoint.index;
        this.selectedProduct = this.topProducts[productIndex];
      }
    },

    initChart() {
      const ctx = document.getElementById("combinedChart");
      this.chart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: this.topProducts.map(
            (product) => `${product.tenAoDai} (${product.maAoDai})`
          ),
          datasets: [
            {
              label: "Doanh thu",
              data: this.topProducts.map((product) => product.tongTienDaBan),
              backgroundColor: "rgba(255, 99, 132, 0.6)",
              borderColor: "rgb(255, 99, 132)",
              borderWidth: 2,
              order: 1,
              barThickness: 80,
              yAxisID: "y-axis-revenue",
            },
            {
              label: "Số lượng bán",
              data: this.topProducts.map((product) => product.tongSoLuongDaBan),
              type: "line",
              borderColor: "rgb(54, 162, 235)",
              borderWidth: 3,
              pointBackgroundColor: "rgb(54, 162, 235)",
              pointBorderColor: "#fff",
              pointHoverBackgroundColor: "#fff",
              pointHoverBorderColor: "rgb(54, 162, 235)",
              pointRadius: 6,
              pointHoverRadius: 8,
              fill: false,
              tension: 0.4,
              order: 0,
              yAxisID: "y-axis-quantity",
            },
          ],
        },
        options: {
          responsive: true,
          interaction: {
            mode: "index",
            intersect: false,
          },
          plugins: {
            legend: {
              position: "top",
              labels: {
                usePointStyle: true,
                padding: 20,
              },
            },
            tooltip: {
              backgroundColor: "rgba(0, 0, 0, 0.8)",
              padding: 12,
              titleFont: {
                size: 14,
                weight: "bold",
              },
              bodyFont: {
                size: 13,
              },
              callbacks: {
                label: (context) => {
                  if (context.dataset.label === "Doanh thu") {
                    return (
                      context.dataset.label +
                      ": " +
                      this.formatCurrency(context.raw)
                    );
                  }
                  return context.dataset.label + ": " + context.raw;
                },
              },
            },
          },
          scales: {
            "y-axis-revenue": {
              type: "linear",
              position: "left",
              title: {
                display: true,
                text: "Doanh thu (VNĐ)",
                font: {
                  weight: "bold",
                },
              },
              ticks: {
                callback: (value) => this.formatCurrency(value),
              },
              grid: {
                drawOnChartArea: false,
              },
            },
            "y-axis-quantity": {
              type: "linear",
              position: "right",
              title: {
                display: true,
                text: "Số lượng bán (Bộ)",
                font: {
                  weight: "bold",
                },
              },
              ticks: {
                stepSize: 1,
                callback: (value) => Math.floor(value),
              },
              grid: {
                drawOnChartArea: false,
              },
            },
          },
          onClick: (event, elements, chart) => {
            this.handleChartClick(chart, event);
          },
        },
      });
    },
    // doanh thu trong ngày
    async fetchDailyRevenue() {
      try {
        const response = await ThongKeService.getDoanhThuTrongNgay(
          this.selectedDate
        );
        this.dailyRevenue = response.data.body[0] || null;
        if (this.dailyRevenueChart) {
          this.dailyRevenueChart.destroy();
        }
        if (this.dailyRevenue) {
          this.initDailyRevenueChart();
        }
      } catch (error) {
        console.error("Error fetching daily revenue:", error);
        this.$toast.error("Không thể tải dữ liệu doanh thu trong ngày");
      }
    },
    initDailyRevenueChart() {
      if (!this.dailyRevenue) return;

      const ctx = document.getElementById("dailyRevenueChart");
      this.dailyRevenueChart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: ["Doanh Thu Trong Ngày"],
          datasets: [
            {
              label: "Tổng Doanh Thu",
              data: [this.dailyRevenue.tongDoanhThu],
              backgroundColor: "rgba(40, 167, 69, 0.6)",
              borderColor: "rgb(40, 167, 69)",
              borderWidth: 2,
            },
            {
              label: "Phí Ship",
              data: [this.dailyRevenue.tongPhiShip],
              backgroundColor: "rgba(255, 193, 7, 0.6)",
              borderColor: "rgb(255, 193, 7)",
              borderWidth: 2,
            },
            {
              label: "Doanh Thu Thực Tế",
              data: [this.dailyRevenue.doanhThuThucTe],
              backgroundColor: "rgba(23, 162, 184, 0.6)",
              borderColor: "rgb(23, 162, 184)",
              borderWidth: 2,
            },
          ],
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  return (
                    context.dataset.label +
                    ": " +
                    this.formatCurrency(context.raw)
                  );
                },
              },
            },
          },
          scales: {
            y: {
              beginAtZero: true,
              ticks: {
                callback: (value) => this.formatCurrency(value),
              },
            },
          },
        },
      });
    },
    //doanh thu theo thang

    async fetchMonthlyRevenue() {
      try {
        const response = await ThongKeService.getDoanhThuTheoThang(
          this.selectedMonth,
          this.selectedYear
        );
        this.monthlyRevenue = response.data.body;
        if (this.monthlyRevenueChart) {
          this.monthlyRevenueChart.destroy();
        }
        this.initMonthlyRevenueChart();
      } catch (error) {
        console.error("Error fetching monthly revenue:", error);
        this.$toast.error("Không thể tải dữ liệu doanh thu theo tháng");
      }
    },
    initMonthlyRevenueChart() {
      const ctx = document.getElementById("monthlyRevenueChart");

      // Sắp xếp dữ liệu theo ngày
      const sortedData = [...this.monthlyRevenue].sort(
        (a, b) => a.ngay - b.ngay
      );

      this.monthlyRevenueChart = new Chart(ctx, {
        type: "line",
        data: {
          labels: sortedData.map((item) => `Ngày ${item.ngay}`),
          datasets: [
            {
              label: "Tổng Doanh Thu Ngày",
              data: sortedData.map((item) => item.tongDoanhThuNgay),
              borderColor: "rgb(40, 167, 69)", // Xanh lá cây
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(40, 167, 69)",
            },
            {
              label: "Doanh Thu Thực Tế",
              data: sortedData.map((item) => item.tongDoanhThuThucTe),
              borderColor: "rgb(23, 162, 184)", // Xanh dương
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(23, 162, 184)",
            },
            {
              label: "Phí Ship",
              data: sortedData.map((item) => item.tongPhiShip),
              borderColor: "rgb(255, 193, 7)", // Vàng
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(255, 193, 7)",
              borderDash: [5, 5],
            },
            {
              label: "Tổng Số Hóa Đơn",
              data: sortedData.map((item) => item.tongSoHoaDon),
              borderColor: "rgb(220, 53, 69)", // Đỏ
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(220, 53, 69)",
              borderDash: [5, 5],
              yAxisID: "y-axis-invoice",
            },
          ],
        },

        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  return (
                    context.dataset.label +
                    ": " +
                    this.formatCurrency(context.raw)
                  );
                },
              },
            },
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              beginAtZero: true,
              ticks: {
                callback: (value) => this.formatCurrency(value),
              },
            },
            "y-axis-invoice": {
              type: "linear",
              position: "right",
              title: {
                display: true,
                text: "Số lượng hóa đơn",
                font: { weight: "bold" },
              },
              ticks: {
                stepSize: 1,
                precision: 0,
              },
              grid: {
                drawOnChartArea: false,
              },
            },
          },
          interaction: {
            intersect: false,
            mode: "index",
          },
        },
      });
    },
    // theo năm
    async fetchYearlyRevenue() {
      try {
        const response = await ThongKeService.getDoanhThuTheoNam(
          this.selectedYearRevenue
        );
        this.yearlyRevenue = response.data.body;
        if (this.yearlyRevenueChart) {
          this.yearlyRevenueChart.destroy();
        }
        this.initYearlyRevenueChart();
      } catch (error) {
        console.error("Error fetching yearly revenue:", error);
        this.$toast.error("Không thể tải dữ liệu doanh thu theo năm");
      }
    },

    initYearlyRevenueChart() {
      const ctx = document.getElementById("yearlyRevenueChart");

      const monthsData = Array(12).fill({
        tongDoanhThuThang: 0,
        doanhThuThucTe: 0,
        tongPhiShip: 0,
        tongSoHoaDon: 0,
      });

      this.yearlyRevenue.forEach((item) => {
        monthsData[item.thang - 1] = item;
      });

      this.yearlyRevenueChart = new Chart(ctx, {
        type: "line",
        data: {
          labels: [
            "Tháng 1",
            "Tháng 2",
            "Tháng 3",
            "Tháng 4",
            "Tháng 5",
            "Tháng 6",
            "Tháng 7",
            "Tháng 8",
            "Tháng 9",
            "Tháng 10",
            "Tháng 11",
            "Tháng 12",
          ],
          datasets: [
            {
              label: "Tổng Doanh Thu",
              data: monthsData.map((item) => item.tongDoanhThuThang),
              borderColor: "rgb(40, 167, 69)", // Xanh lá cây
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(40, 167, 69)",
            },
            {
              label: "Doanh Thu Thực Tế",
              data: monthsData.map((item) => item.doanhThuThucTe),
              borderColor: "rgb(23, 162, 184)", // Xanh dương
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(23, 162, 184)",
            },
            {
              label: "Phí Ship",
              data: monthsData.map((item) => item.tongPhiShip),
              borderColor: "rgb(255, 193, 7)", // Vàng
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(255, 193, 7)",
              borderDash: [5, 5],
            },
            {
              label: "Số Hóa Đơn",
              data: monthsData.map((item) => item.tongSoHoaDon),
              borderColor: "rgb(220, 53, 69)", // Đỏ
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(220, 53, 69)",
              borderDash: [5, 5],
              yAxisID: "y-axis-invoice",
            },
          ],
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  if (context.dataset.label === "Số Hóa Đơn") {
                    return context.dataset.label + ": " + context.raw;
                  }
                  return (
                    context.dataset.label +
                    ": " +
                    this.formatCurrency(context.raw)
                  );
                },
              },
            },
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              beginAtZero: true,
              ticks: {
                callback: (value) => this.formatCurrency(value),
              },
            },
            "y-axis-invoice": {
              type: "linear",
              position: "right",
              title: {
                display: true,
                text: "Số lượng hóa đơn",
                font: { weight: "bold" },
              },
              ticks: {
                stepSize: 1,
                precision: 0,
              },
              grid: {
                drawOnChartArea: false,
              },
            },
          },
          interaction: {
            intersect: false,
            mode: "index",
          },
        },
      });
    },

    // theo khoảng thời gian

    async fetchDateRangeRevenue() {
      try {
        const response = await ThongKeService.getDoanhThuTheoKhoangThoiGian(
          this.startDate,
          this.endDate
        );
        this.dateRangeRevenue = response.data.body;

        if (this.dateRangeRevenueChart) {
          this.dateRangeRevenueChart.destroy();
        }
        if (this.dateRangeRevenue.length > 0) {
          this.initDateRangeRevenueChart();
        }
      } catch (error) {
        console.error("Error fetching date range revenue:", error);
        this.$toast.error(
          "Không thể tải dữ liệu doanh thu theo khoảng thời gian"
        );
      }
    },

    initDateRangeRevenueChart() {
      const ctx = document.getElementById("dateRangeRevenueChart");

      const sortedData = [...this.dateRangeRevenue].sort(
        (a, b) => new Date(a.ngay) - new Date(b.ngay)
      );

      this.dateRangeRevenueChart = new Chart(ctx, {
        type: "line",
        data: {
          labels: sortedData.map((item) => {
            const date = new Date(item.ngay);
            return date.toLocaleDateString("vi-VN", {
              day: "2-digit",
              month: "2-digit",
              year: "numeric",
            });
          }),
          datasets: [
            {
              label: "Tổng Doanh Thu",
              data: sortedData.map((item) => item.tongDoanhThu),
              borderColor: "rgb(40, 167, 69)", // Green
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(40, 167, 69)",
            },
            {
              label: "Doanh Thu Thực Tế",
              data: sortedData.map((item) => item.doanhThuThucTe),
              borderColor: "rgb(23, 162, 184)", // Blue
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(23, 162, 184)",
            },
            {
              label: "Phí Ship",
              data: sortedData.map((item) => item.tongPhiShip),
              borderColor: "rgb(255, 193, 7)", // Yellow
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(255, 193, 7)",
              borderDash: [5, 5],
            },
            {
              label: "Giảm Giá",
              data: sortedData.map((item) => item.tongGiamGia),
              borderColor: "rgb(111, 66, 193)", // Purple
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(111, 66, 193)",
              borderDash: [5, 5],
            },
            {
              label: "Số Hóa Đơn",
              data: sortedData.map((item) => item.tongHoaDon),
              borderColor: "rgb(220, 53, 69)", // Red
              borderWidth: 2,
              tension: 0.4,
              pointBackgroundColor: "rgb(220, 53, 69)",
              borderDash: [5, 5],
              yAxisID: "y-axis-invoice",
            },
          ],
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: "top",
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  if (context.dataset.label === "Số Hóa Đơn") {
                    return context.dataset.label + ": " + context.raw;
                  }
                  return (
                    context.dataset.label +
                    ": " +
                    this.formatCurrency(context.raw)
                  );
                },
              },
            },
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
            },
            y: {
              beginAtZero: true,
              ticks: {
                callback: (value) => this.formatCurrency(value),
              },
            },
            "y-axis-invoice": {
              type: "linear",
              position: "right",
              title: {
                display: true,
                text: "Số lượng hóa đơn",
                font: { weight: "bold" },
              },
              ticks: {
                stepSize: 1,
                precision: 0,
              },
              grid: {
                drawOnChartArea: false,
              },
            },
          },
          interaction: {
            intersect: false,
            mode: "index",
          },
        },
      });
    },

    // phân bố loại áo dài

    async fetchLoaiAoDaiData() {
      try {
        const response = await ThongKeService.getLoaiAoDaiBanNhieu();
        this.loaiAoDaiData = response.data.body;
      } catch (error) {
        console.error("Error fetching loai ao dai data:", error);
        this.$toast.error("Không thể tải dữ liệu thống kê loại áo dài");
      }
    },

    initPieChart() {
      const ctx = document.getElementById("pieChart");

      const colors = this.loaiAoDaiData.map(
        () => `hsl(${Math.random() * 360}, 70%, 50%)`
      );

      this.pieChart = new Chart(ctx, {
        type: "pie",
        data: {
          labels: this.loaiAoDaiData.map((item) => item.tenLoaiAoDai),
          datasets: [
            {
              data: this.loaiAoDaiData.map((item) => item.phanTram),
              backgroundColor: colors,
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          layout: {
            padding: 10,
          },
          plugins: {
            legend: {
              position: "right",
              labels: {
                padding: 10,
                usePointStyle: true,
              },
              font: {
              size: 10 
            }
            },
            tooltip: {
              callbacks: {
                label: (context) => {
                  const label = context.label || "";
                  const value = context.raw || 0;
                  const soLuong = this.loaiAoDaiData[context.dataIndex].soLuong;
                  return `${label}: ${value}% (${soLuong} sản phẩm)`;
                },
              },
            },
            title: {
              display: false,
              text: "Phân Bố Loại Áo Dài",
              font: {
                size: 16,
                weight: "bold",
              },
            },
          },
        },
      });
    },
  },
};
</script>

<style scoped>
.card {
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.card:hover {
  transform: translateY(-5px);
}

.form-control {
  border-radius: 4px;
  border: 1px solid #ced4da;
  padding: 0.375rem 0.75rem;
}

.form-control:focus {
  border-color: #80bdff;
  outline: 0;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>
