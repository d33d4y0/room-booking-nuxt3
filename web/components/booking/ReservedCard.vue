<template>
  <div class="rounded-lg bg-white border border-black px-4 py-4">
    <span class="mb-4 block text-lg font-bold">Booking No. {{ bookingId }}</span>
    <div class="mb-4 grid grid-cols-2 gap-2">
      <p class="font-semibold break-words">{{ roomName }}</p>
      <p class="font-semibold break-words">{{ maxGuests }} Guests max</p>
      <p class="text-gray-600 break-words">{{ date }}</p>
      <p class="text-gray-600 break-words">{{ startTime }} - {{ endTime }}</p>
    </div>
    <button
      @click="handleCancel"
      class="w-full rounded-full bg-red-400 px-4 py-3 font-bold text-white transition duration-300 hover:bg-red-500"
    >
      Cancel
    </button>
  </div>
</template>

<script setup lang="ts">
interface BookingCardProps {
  bookingId: number;
  roomName: string;
  maxGuests: number;
  date: string;
  startTime: string;
  endTime: string;
}

const props = defineProps<BookingCardProps>();

const emit = defineEmits<{
  (e: "cancel", bookingId: number): void;
}>();

const handleCancel = () => {
  emit("cancel", props.bookingId);
};
</script>