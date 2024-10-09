// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: "2024-04-03",
  devtools: { enabled: true },
  nitro: {
    compressPublicAssets: true,
    routeRules: {
      // "/_nuxt/**": { headers: { "cache-control": "max-age=31536000" } }, // Set generated files cache to 1 year
    },
  },
  // ssr: true,
  // routeRules: {
  //   "/booking/**": { appMiddleware: ["auth"] },
  // },
  imports: {
    dirs: ["services/*"],
  },
  modules: ["@nuxtjs/tailwindcss", "nuxt-typed-router", "@pinia/nuxt"],
});
