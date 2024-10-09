export default interface BookingHistoryDetail {
    id: number;
    date: string;
    startTime: string;
    endTime: string;
    roomId: number;
    roomName: string;
    maxCapacity: number;
    status: BookingStatus;
}

enum BookingStatus {
    RESERVED = "RESERVED",
    COMPLETED = "COMPLETED",
    CANCELLED = "CANCELLED"
}