/** @type {import("tailwindcss").Config} */
export default {
  content: [
    './components/**/*.{vue,js,ts}',
    './layouts/**/*.{vue,js,ts}',
    './pages/**/*.{vue,js,ts}',
    './plugins/**/*.{js,ts}',
    './app.{vue,js,ts}',
    './error.{vue,js,ts}',
    './nuxt.config.{js,ts}',
  ],
  theme: {
    screens: {
      "sm": "640px",
      "md": "768px",
      "lg": "1024px",
      "xl": "1280px",
      "2xl": "1536px",
    },
    extend: {
      colors: {
        "pastel-green": '#5CC99B',
      },
      textColor: {
        "pastel-green": '#5CC99B',
      },
      backgroundColor: {
        "pastel-green": '#5CC99B',
      },
    },
  },
  plugins: [],
}

