<template>
  <div class="error-page">
    <h1>Page not found: {{ route.path }}</h1>
  </div>
</template>

<script setup lang="ts">
const route = useRoute();
</script>

<style scoped>
.error-page {
  text-align: center;
  margin: 50px auto;
}
</style>
