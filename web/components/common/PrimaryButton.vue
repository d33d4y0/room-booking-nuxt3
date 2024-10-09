<template>
  <button
    :disabled="props.disabled ? props.disabled : false"
    :type="props.type ? props.type : 'button'"
    class="rounded-lg bg-pastel-green py-6 text-center font-semibold text-white"
    @click="handleClick"
  >
    <slot />
  </button>
</template>

<script lang="ts" setup>
type Props = {
  disabled?: boolean;
  type?: "button" | "submit" | "reset";
  onClick?: () => void;
};
const props = defineProps<Props>();

const handleClick = () => {
  if (props.onClick && !props.disabled) {
    props.onClick();
  }
};
</script>

<style scoped>
button:disabled {
  background-color: #d3d3ce;
  cursor: not-allowed;
}
</style>
