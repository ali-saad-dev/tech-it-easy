package nl.novi.techiteasy.helpper;

import nl.novi.techiteasy.dtos.*;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.WallBracket;

public class Mapper {
    public static TelevisionOutPutDto fromTelevisionToDto(Television television){
        TelevisionOutPutDto dto = new TelevisionOutPutDto();
        dto.id = television.getId();
        dto.name = television.getName();
        dto.price = television.getPrice();
        return dto;
    }

    public static Television fromDtoToTelevision(TelevisionInputDto dto){
        Television television = new Television();
        television.setName(dto.name);
        television.setPrice(dto.price);
        return  television;
    }

    public static RemoteControllerOutPutDto convertRemoteControllerToRemoteControllerOutPutDto (RemoteController remoteController){
        RemoteControllerOutPutDto remoteControllerDto = new RemoteControllerOutPutDto();
        remoteControllerDto.id = remoteController.getId();
        remoteControllerDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllerDto.batteryType = remoteController.getBatteryType();
        remoteControllerDto.name = remoteController.getName();
        remoteControllerDto.brand = remoteController.getBrand();
        remoteControllerDto.price = remoteController.getPrice();
        remoteControllerDto.originalStock = remoteController.getOriginalStock();

        return remoteControllerDto;
    }

    public static RemoteController fromDtoToToRemoteController(RemoteControllerInputDto dto) {
        RemoteController remoteController = new RemoteController();
        remoteController.setCompatibleWith(dto.compatibleWith);
        remoteController.setBatteryType(dto.batteryType);
        remoteController.setName(dto.name);
        remoteController.setBrand(dto.brand);
        remoteController.setPrice(dto.price);
        remoteController.setOriginalStock(dto.originalStock);

        return remoteController;
    }

    public static WallBracket fromDtoToWallBracket(WallBracketInputDto wallBracketInputDto){
        WallBracket wallBracket = new WallBracket();
        wallBracket.setName(wallBracketInputDto.name);
        wallBracket.setSize(wallBracketInputDto.size);
        wallBracket.setAdjustable(wallBracketInputDto.adjustable);
        wallBracket.setPrice(wallBracketInputDto.price);

        return wallBracket;
    }

    public static WallBracketOutPutDto fromWallBracketToDto(WallBracket wallBracket){
        WallBracketOutPutDto dto = new WallBracketOutPutDto();

        dto.id = (wallBracket.getId());
        dto.name = (wallBracket.getName());
        dto.size =(wallBracket.getSize());
        dto.adjustable =(wallBracket.getAdjustable());
        dto.price =(wallBracket.getPrice());

        return dto;
    }
}
