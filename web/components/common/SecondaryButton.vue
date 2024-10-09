<template>
  <button
    :disabled="props.disabled ? props.disabled : false"
    :type="props.type ? props.type : 'button'"
    class="border-pastel-green border-[1px] rounded-lg py-6 text-center text-pastel-green font-semibold"
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
