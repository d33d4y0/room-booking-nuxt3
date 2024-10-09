<template>
  <teleport to="body">
    <div class="modal-overlay" v-show="isVisible" @click="handleOverlayClick">
      <div class="modal" @click.stop>
        <slot></slot>
      </div>
    </div>
  </teleport>
</template>
<script setup lang="ts">
interface Props {
  isVisible: boolean;
}
const props = defineProps<Props>();
  
const emit = defineEmits<{
  (e: "close-modal"): void;
}>();

const handleOverlayClick = () => {
  emit("close-modal");
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: grid;
  place-items: center;
  z-index: 1000;
}

.modal {
  background-color: white;
  height: 500px;
  width: 500px;
  border-radius: 20px;
}
</style>
