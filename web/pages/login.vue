<template>
  <HomeBackground>
    <div class="flex justify-between">
      <div>
        <p class="mb-5 mt-4 text-5xl font-semibold text-white">Hello !</p>
        <p class="text-5xl font-semibold text-white">Welcome Back</p>
      </div>
      <UCard class="w-1/2 px-8 py-10">
        <form @submit.prevent="handleSubmit" class="flex flex-col">
          <div class="flex flex-grow flex-col gap-5">
            <div class="flex flex-col">
              <label for="email-input" class="text-gray-400">Email</label>
              <input
                v-model="email"
                type="email"
                name="email"
                id="email-input"
                placeholder="Email"
                class="rounded-lg border border-gray-400 p-4"
                :disabled="isLoading"
              />
            </div>
            <div class="flex flex-col">
              <label for="password-input" class="text-gray-400">Password</label>
              <input
                v-model="password"
                type="password"
                name="password"
                id="password-input"
                placeholder="Password"
                class="rounded-lg border border-gray-400 p-4"
                :disabled="isLoading"
              />
            </div>
          </div>

          <div class="mt-4 h-16">
            <div v-show="showError" class="rounded-xl bg-red-300 p-4">
              <p>{{ errorMessage }}</p>
            </div>
          </div>

          <CommonPrimaryButton class="mt-4" type="submit" :disabled="isLoading">
            {{ isLoading ? "Logging in..." : "Login" }}
          </CommonPrimaryButton>
        </form>
      </UCard>
    </div>
  </HomeBackground>
</template>

<script setup lang="ts">
const { login } = useAuth();
const email = ref<string>("test@gmail.com");
const password = ref<string>("password");
const showError = ref<boolean>(false);
const errorMessage = ref<string>("");
const isLoading = ref<boolean>(false);

const handleSubmit = async () => {
  if (isLoading.value) return;

  isLoading.value = true;
  showError.value = false;
  errorMessage.value = "";

  try {
    login(email.value, password.value);
  } catch (error) {
    errorMessage.value = "Invalid email or password. Please try again.";
    showError.value = true;
  } finally {
    isLoading.value = false;
  }
};
</script>

<style scoped></style>
