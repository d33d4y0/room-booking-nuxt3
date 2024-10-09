<template>
  <BookingNavBar />
  <section class="mx-28 mt-6 flex flex-col">
    <button class="flex justify-end" @click="goToBookingPage">
      <IconCalandarAdd class="pr-2" /> Make New Booking
    </button>
    <span class="font-semibold">Reserved</span>
    <div v-if="loading">Loading...</div>
    <div
      v-else-if="bookingList.length > 0"
      class="grid grid-cols-3 gap-4 2xl:grid-cols-4"
    >
      <BookingReservedCard
        v-for="booking in bookingList"
        :key="booking.id"
        :bookingId="booking.id"
        :roomName="booking.roomName"
        :maxGuests="booking.maxCapacity"
        :date="booking.date"
        :startTime="booking.startTime"
        :endTime="booking.endTime"
        @cancel="handleCancelBooking(booking.id)"
      />
    </div>
    <p v-else>No booking history available.</p>
  </section>
</template>

<script lang="ts" setup>
import type BookingHistoryDetail from "~/types/service/booking/BookingHistoryDetail";

const router = useRouter();
const bookingList = ref<BookingHistoryDetail[]>([]);
const loading = ref(true);

const fetchBookingHistory = async () => {
  loading.value = true;
  try {
    const response = await bookingService.fetchBookingHistory();
    bookingList.value = response.data || [];
  } catch (error) {
    console.error("Error fetching booking history:", error);
    bookingList.value = [];
  } finally {
    loading.value = false;
  }
};

onMounted(fetchBookingHistory);

const handleCancelBooking = async (bookingId: number) => {
  await bookingService.cancelBooking(bookingId);
  await fetchBookingHistory();
};
const goToBookingPage = () => {
  router.push("/booking");
};
</script>
