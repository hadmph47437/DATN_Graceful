<!-- App.vue -->
<template>
  <div id="app">
    <component :is="layoutComponent" />
  </div>
</template>

<script>
import AdminLayout from './layouts/AdminLayout.vue';
import CustomerLayout from './layouts/CustomerLayout.vue';

export default {
  name: 'App',
  components: {
    AdminLayout,
    CustomerLayout,
  },
  computed: {
    layoutComponent() {
      return this.$route.meta && this.$route.meta.isAdmin
        ? AdminLayout
        : CustomerLayout;
    },
  },
};
</script>