export const useAuth = () => {
  const router = useRouter();
  const logout = () => {
    // Delete the JWT cookie
    // const token = useCookie("jwt-token");
    // token.value = null;
    // TODO clear cookie
    // Clear any local state (assuming you're using Pinia)
    // const store = useAuthStore();
    // store.$reset();

    router.push("/");
  };

  const login = async (email: string, password: string) => {
    await authService.login(email, password);
    router.push("/booking");
  };

  const createAccount = async (
    firstName: string,
    lastName: string,
    email: string,
    password: string,
  ) => {
    await authService.signup(firstName, lastName, email, password);
    router.push("/login");
  };

  return {
    logout,
    login,
    createAccount,
  };
};
