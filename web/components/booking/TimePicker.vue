<template>
  <div class="flex flex-col">
    <label :for="label" class="text-gray-400">{{ label }}</label>
    <div class="relative">
      <button
        :id="label"
        @click="toggleDropdown"
        class="w-full rounded-lg border border-gray-400 bg-white p-4 text-left shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        <span :class="{ 'text-gray-400': !modelValue }">
          {{ modelValue || placeholder }}
        </span>
      </button>
      <ul
        v-if="isOpen"
        class="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-lg border bg-white shadow-lg"
      >
        <li
          v-for="time in timeMap.keys()"
          :key="time"
          @click="selectTime(time)"
          class="cursor-pointer p-2 hover:bg-pastel-green hover:rounded-xl"
        >
          {{ time }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup lang="ts">
import timeMap from "~/constants/time";

const props = defineProps({
  modelValue: {
    type: String,
    required: true,
  },
  label: {
    type: String,
    required: true,
  },
  placeholder: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(["update:modelValue"]);
const isOpen = ref(false);

const toggleDropdown = () => {
  isOpen.value = !isOpen.value;
};

const selectTime = (time: string) => {
  emit("update:modelValue", time);
  isOpen.value = false;
};

// Watch for external changes to modelValue
watch(
  () => props.modelValue,
  (newValue) => {
    if (newValue && !timeMap.has(newValue)) {
      console.warn(`Invalid time value: ${newValue}`);
      emit("update:modelValue", "");
    }
  },
);
</script>
