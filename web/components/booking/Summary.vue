<template>
  <div class="flex">
    <UModal
      :isVisible="isVisible"
      @close-modal="$emit('update:isVisible', false)"
    >
      <div class="flex min-h-full flex-col justify-between px-8 pb-8 pt-8">
        <div>
          <span class="text-2xl font-bold">Booking Summary</span>
          <div class="mt-2 grid grid-cols-5 gap-3">
            <div class="col-span-2">
              <BookingSummaryTag label="Date" :content="formattedDate">
                {{ formattedDate }}
              </BookingSummaryTag>
            </div>
            <div class="col-span-3">
              <BookingSummaryTag label="Time" :content="formattedTime">
                {{ formattedTime }}
              </BookingSummaryTag>
            </div>
            <div class="col-span-5">
              <BookingSummaryTag label="Selected Room" :content="selectedRoom.roomName">
                <div class="flex justify-between px-4">
                    <span class="font-semibold">{{ selectedRoom.roomName }}</span>
                    <span class="text-sm">{{ selectedRoom.maxCapacity }} Guests max</span>
                </div>
              </BookingSummaryTag>
            </div>
          </div>
        </div>
        <div class="mb-3 flex flex-col gap-4">
          <CommonSecondaryButton
            class="min-w-full"
            id="change-room-button"
            @click="$emit('cancel')"
          >
            Cancel
          </CommonSecondaryButton>
          <CommonPrimaryButton
            class="min-w-full"
            id="confirm-booking-button"
            @click="$emit('confirm')"
          >
            Confirm Booking
          </CommonPrimaryButton>
        </div>
      </div>
    </UModal>
  </div>
</template>

<script lang="ts" setup>
import { computed } from "vue";

type Props = {
  isVisible: boolean;
  date: string;
  startTime: string;
  endTime: string;
  selectedRoom: {
    roomName: string;
    maxCapacity: number;
  };
};

const props = defineProps<Props>();
const emit = defineEmits(["update:isVisible", "cancel", "confirm"]);

const formattedDate = computed(() => {
  return new Date(props.date).toLocaleDateString("en-US", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });
});

const formattedTime = computed(() => {
  return `${props.startTime} - ${props.endTime}`;
});
</script>
