<template>
  <div class="bottom-0 left-0 h-full w-full overflow-hidden">
    <img
      src="/assets/images/home-background.png"
      class="absolute bottom-0 left-0 min-h-full min-w-full"
    />
    <div class="fixed w-full h-full bg-black bg-opacity-30 bg-left-bottom px-40 py-20">
      <slot />
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
</style>
