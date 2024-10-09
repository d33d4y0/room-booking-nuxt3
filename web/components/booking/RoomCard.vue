<template>
  <div
    class="flex justify-between rounded-lg border border-black px-4 py-6 hover:border-pastel-green hover:bg-pastel-green hover:text-white cursor-pointer"
    @click="onClick"
    >
    <span class="font-bold">{{ roomName }}</span>
    <span>{{ maxCapacity }} Guests max</span>
  </div>
</template>

<script lang="ts" setup>
import { defineProps } from "vue";

type Props = {
  roomName: String;
  maxCapacity: Number;
  onClick?: () => void;
};
const props = defineProps<Props>();
</script>

<style></style>
