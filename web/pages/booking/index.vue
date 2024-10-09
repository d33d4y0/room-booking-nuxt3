<template>
  <div>
    <BookingNavBar />
    <section class="mx-28 mt-6 flex flex-col">
      <button class="flex justify-end" @click="goToBookingHistoryPage">
        <IconCalendarCheck class="pr-2" />My Booking
      </button>
      <div class="grid grid-cols-4 gap-4">
        <CommonTextInput
          label="Number of Guest"
          type="text"
          name="guest"
          id="guest-number-input"
          placeholder="Select number of guest"
          v-model.number="numberOfGuest"
        />
        <BookingDateInput
          label="Date"
          name="date"
          id="date-input"
          placeholder="Select date"
          v-model="date"
        />
        <BookingTimePicker
          label="Start Time"
          name="start-time"
          id="start-time-input"
          placeholder="Select start time"
          v-model="startTime"
        />
        <BookingTimePicker
          label="End Time"
          name="end-time"
          id="end-time-input"
          placeholder="Select end time"
          v-model="endTime"
        />
      </div>
      <div class="flex justify-end">
        <CommonPrimaryButton
          class="mt-6 min-w-60"
          id="find-room-button"
          :disabled="!isAllInputFilled"
          @click="handleFindRoom"
          >Find Room</CommonPrimaryButton
        >
      </div>
      <span class="font-semibold">Available Room</span>
      <div class="grid grid-cols-3 gap-4 2xl:grid-cols-4">
        <BookingRoomCard
          v-for="room in rooms"
          :key="room.roomId"
          :roomName="room.roomName"
          :maxCapacity="room.maxCapacity"
          @click="showSummary(room)"
        />
      </div>
      <BookingSummary
        v-model:isVisible="isShowModal"
        :date="date"
        :startTime="startTime"
        :endTime="endTime"
        :selectedRoom="selectedRoom"
        @cancel="cancelBookingSummary"
        @confirm="confirmBooking"
      />
    </section>
  </div>
</template>

<script lang="ts" setup>
import timeMap from "~/constants/time";
import type RoomDetail from "~/types/service/booking/RoomDetail";

const router = useRouter();
const isShowModal = ref<boolean>(false);
const numberOfGuest = ref<number>(2);
const date = ref<string>("2024-12-25");
const startTime = ref<string>("8:00 PM");
const endTime = ref<string>("9:00 PM");
const rooms = ref<RoomDetail[]>([]);
const selectedRoom = ref<RoomDetail>({} as RoomDetail);

const isAllInputFilled = computed<boolean>(() => {
  return (
    !!numberOfGuest.value &&
    !!date.value &&
    !!startTime.value &&
    !!endTime.value
  );
});

const handleFindRoom = async () => {
  const convertedStartTime = timeMap.get(startTime.value);
  const convertedEndTime = timeMap.get(endTime.value);
  const response = await bookingService.fetchRoom(
    numberOfGuest.value,
    date.value,
    convertedStartTime,
    convertedEndTime,
  );
  rooms.value = response.data;
};

const showSummary = async (room: RoomDetail) => {
  const roomDetail = await bookingService.fetchRoomDetail(room.roomId);
  selectedRoom.value = roomDetail;
  isShowModal.value = true;
};

const cancelBookingSummary = () => {
  isShowModal.value = false;
  selectedRoom.value = {} as RoomDetail;
};

const confirmBooking = async () => {
  await bookingService.bookRoom(
    selectedRoom.value.roomId,
    date.value,
    startTime.value,
    endTime.value,
  );
  isShowModal.value = false;
  goToBookingHistoryPage();
  // TODO show success message or navigate to booking history page
};
const goToBookingHistoryPage = () => {
  router.push("/booking/history");
};
</script>
