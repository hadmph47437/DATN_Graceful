<!-- layouts/AdminLayout.vue -->
<template>
    <div class="admin-layout">
        <Header />
        <div class="admin-content">
            <router-view />
        </div>
    </div>
</template>

<script>
import Header from '../components/login/Header.vue';

export default {
    name: 'AdminLayout',
    components: {
        Header,
    },
};
</script>

<style scoped>
.admin-content {
    margin-left: 240px;
}
</style>