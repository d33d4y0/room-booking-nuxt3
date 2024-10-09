<template>
  <div>
    <span class="font-semibold">{{ props.label }}</span>
    <div class="rounded-lg border border-black py-4 text-center">
      <!-- {{ props.content }} -->
      <slot />
    </div>
  </div>
</template>

<script lang="ts" setup>
interface Props {
  label: string;
  content: string;
}
const props = defineProps<Props>();
</script>

<style></style>
