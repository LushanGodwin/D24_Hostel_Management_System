package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    boolean saveRoom(RoomDTO roomDTO);

    List<RoomDTO> getAllRoom();

    RoomDTO searchRoom(String roomId);

    String generatenextStudentId();

    boolean updateRoom(RoomDTO roomDTO);


    boolean deleteRoom(String roomId);

}
