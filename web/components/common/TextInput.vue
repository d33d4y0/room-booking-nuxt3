<template>
  <div class="flex flex-col">
    <label :for="id" class="text-gray-400">{{ label }}</label>
    <input
      :type="type"
      :name="name"
      :id="id"
      :placeholder="placeholder"
      class="rounded-lg border border-gray-400 p-4 text-center"
      @input="
        $event.target &&
          $emit('update:modelValue', ($event.target as HTMLInputElement).value)
      "
      :value="modelValue"
      required
    />
  </div>
</template>

<script lang="ts" setup>
import { defineProps } from "vue";

const props = defineProps({
  modelValue: {
      required: true,
  },
  label: {
    type: String,
    required: true,
  },
  type: {
    type: String,
    required: true,
  },
  name: {
    type: String,
    required: true,
  },
  id: {
    type: String,
    required: true,
  },
  placeholder: {
    type: String,
    required: true,
  },
});
</script>

<style></style>
