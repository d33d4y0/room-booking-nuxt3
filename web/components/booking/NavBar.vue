<template>
  <nav
    class="relative max-h-24 min-h-28 min-w-full overflow-hidden rounded-b-[48px] shadow-md"
  >
    <img src="/assets/images/booking-navbar.png" class="min-w-full" />
    <div
      class="absolute left-0 top-0 flex h-full w-full justify-between bg-black bg-opacity-30 p-4"
    >
      <span class="ml-32 mt-8 text-3xl font-semibold text-white">
        Make My Booking
      </span>
      <button class="mr-24 mt-2 font-thin text-white" @click="handleLogout">
        Logout
      </button>
    </div>
  </nav>
</template>

<script lang="ts" setup>
const { logout } = useAuth();
const handleLogout = () => {
  logout();
};
</script>

<style></style>
