<template>
  <HomeBackground>
    <div class="flex justify-between">
      <div>
        <p class="mb-5 mt-4 text-5xl font-semibold text-white">Meeting</p>
        <p class="text-5xl font-semibold text-white">Room Booking</p>
      </div>
      <UCard class="flex w-1/2 flex-col gap-16 px-8 py-10">
        <div class="flex flex-col gap-8">
          <p>Let's make a meeting room booking easier.</p>
          <p>
            Meeting Room Booking will help you to ensure you will have a room
            for your meeting. Manage reservation, cancellation. ongogin or
            finised booking.
          </p>
        </div>
        <div class="m flex flex-col gap-4">
          <CommonPrimaryButton :on-click="goToLoginPage">Login</CommonPrimaryButton>
          <CommonSecondaryButton :on-click="goToSignupPage">Sign Up</CommonSecondaryButton>
        </div>
      </UCard>
    </div>
  </HomeBackground>
</template>

<script setup lang="ts">
const router = useRouter();

const goToLoginPage = () => {
  router.push("/login");
};
const goToSignupPage = () => {
  console.log("go to signup page");
  router.push("/signup");
};
</script>

<style scoped></style>
