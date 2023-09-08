package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dto.RoomDTO;
import entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    @Override
    public boolean saveRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setQty(roomDTO.getQty());
        return roomDAO.save(room);
    }

    @Override
    public List<RoomDTO> getAllRoom() {
        List<RoomDTO> roomsDTOArrayList = new ArrayList<>();
        List<Room> roomsArrayList = roomDAO.getAll();
        for (Room room : roomsArrayList) {
            RoomDTO roomDTO = new RoomDTO();
            roomDTO.setRoom_type_id(room.getRoom_type_id());
            roomDTO.setType(room.getType());
            roomDTO.setKey_money(room.getKey_money());
            roomDTO.setQty(room.getQty());
            roomsDTOArrayList.add(roomDTO);
        }
        return roomsDTOArrayList;
    }

    @Override
    public RoomDTO searchRoom(String roomId) {
        Room room = roomDAO.search(roomId);
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(room.getRoom_type_id());
        roomDTO.setType(room.getType());
        roomDTO.setKey_money(room.getKey_money());
        roomDTO.setQty(room.getQty());
        return roomDTO;
    }

    @Override
    public String generatenextStudentId() {
        return null;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoom_type_id(roomDTO.getRoom_type_id());
        room.setType(roomDTO.getType());
        room.setKey_money(roomDTO.getKey_money());
        room.setQty(roomDTO.getQty());
        return roomDAO.update(room);
    }

    @Override
    public boolean deleteRoom(String roomId) {
        Room room = new Room();
        room.setRoom_type_id(roomId);
        return roomDAO.delete(room);
    }


}
