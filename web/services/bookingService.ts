import type RoomDetailListResponse from "~/types/service/booking/RoomDetailListResponse";
import axiosInstance from "./axiosInstance";
import type RoomDetail from "~/types/service/booking/RoomDetail";
import type BookingHistoryDetail from "~/types/service/booking/BookingHistoryDetail";
import type DataResponse from "~/types/service/booking/DataResponse";

class BookingService {
  async fetchRoom(
    numberOfGuest: number | undefined,
    date: string | undefined,
    startTime: string | undefined,
    endTime: string | undefined,
  ): Promise<RoomDetailListResponse> {
    try {
      const response = await axiosInstance.get<DataResponse<RoomDetail>>(
        "api/v1/room",
        {
          params: {
            capacity: numberOfGuest,
            date: date,
            start_time: startTime,
            end_time: endTime,
          },
        },
      );
      return response.data;
    } catch (error) {
      console.error("Error fetch available rooms:", error);
      throw error;
    }
  }

  async fetchRoomDetail(roomId: number): Promise<RoomDetail> {
    try {
      const response = await axiosInstance.get<RoomDetail>(
        `api/v1/room/${roomId}`,
      );
      return response.data;
    } catch (error) {
      console.error("Error fetch room detail:", error);
      throw error;
    }
  }

  async bookRoom(
    roomId: number,
    date: string,
    startTime: string,
    endTime: string,
  ): Promise<number> {
    try {
      const response = await axiosInstance.post<number>(
        `api/v1/room/${roomId}/book`,
        {
          date,
          startTime,
          endTime,
        },
      );
      return response.data;
    } catch (error) {
      console.error("Error book room:", error);
      throw error;
    }
  }

  async fetchBookingHistory(): Promise<DataResponse<BookingHistoryDetail>> {
    try {
      const response =
        await axiosInstance.get<DataResponse<BookingHistoryDetail>>("api/v1/booking");
      return response.data;
    } catch (error) {
      console.error("Error fetch booking history:", error);
      throw error;
    }
  }

  async cancelBooking(bookingId: number): Promise<void> {
    try {
      await axiosInstance.delete(`api/v1/booking/${bookingId}`);
    } catch (error) {
      console.error("Error cancel booking:", error);
      throw error;
    }
  }
}

export default new BookingService();
