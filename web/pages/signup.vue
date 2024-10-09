<template>
  <HomeBackground>
    <div class="flex justify-between">
      <div>
        <p class="mb-5 mt-4 text-5xl font-semibold text-white">Create</p>
        <p class="text-5xl font-semibold text-white">New Account</p>
      </div>
      <UCard class="flex w-1/2 flex-col gap-4 px-8 py-10">
        <form @submit.prevent="handleSubmit" class="flex flex-col gap-4">
          <div class="flex flex-col gap-5">
            <div class="flex flex-col">
              <label for="firstName-input" class="text-gray-400"
                >First Name</label
              >
              <input
                type="text"
                name="firstName"
                id="firstName-input"
                placeholder="First Name"
                v-model="formData.firstName"
                class="rounded-lg border border-gray-400 p-4"
                required
              />
            </div>
            <div class="flex flex-col">
              <label for="lastName-input" class="text-gray-400"
                >Last Name</label
              >
              <input
                type="text"
                name="lastName"
                id="lastName-input"
                placeholder="Last Name"
                v-model="formData.lastName"
                class="rounded-lg border border-gray-400 p-4"
                required
              />
            </div>
            <div class="flex flex-col">
              <label for="email-input" class="text-gray-400">Email</label>
              <input
                type="email"
                name="email"
                id="email-input"
                placeholder="Email"
                v-model="formData.email"
                class="rounded-lg border border-gray-400 p-4"
                required
              />
            </div>
            <div class="flex flex-col">
              <label for="password-input" class="text-gray-400">Password</label>
              <input
                type="password"
                name="password"
                id="password-input"
                placeholder="Password"
                v-model="formData.password"
                class="rounded-lg border border-gray-400 p-4"
                required
              />
            </div>
          </div>
          <div class="m flex flex-col gap-4">
            <CommonPrimaryButton :disabled="false" type="submit"
              >Create New Account</CommonPrimaryButton
            >
          </div>
        </form>
      </UCard>
    </div>
  </HomeBackground>
</template>

<script setup lang="ts">
const { createAccount } = useAuth();
const formData = ref({
  firstName: "",
  lastName: "",
  email: "",
  password: "",
});

function handleSubmit() {
  createAccount(
    formData.value.firstName,
    formData.value.lastName,
    formData.value.email,
    formData.value.password,
  );
}
</script>

<style scoped></style>
