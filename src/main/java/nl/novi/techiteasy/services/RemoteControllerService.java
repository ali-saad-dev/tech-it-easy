package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.RemoteControllerInputDto;
import nl.novi.techiteasy.dtos.RemoteControllerOutPutDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.helpper.Mapper;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.repository.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository repos;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepos){
        this.repos = remoteControllerRepos;
    }

    public List<RemoteControllerOutPutDto> getAllRemoteControllers(){
        List<RemoteControllerOutPutDto> remoteControllerDtoList = new ArrayList<>();
        List<RemoteController> remoteControllerList = repos.findAll();
        for (RemoteController remoteController : remoteControllerList) {
            remoteControllerDtoList.add(Mapper.convertRemoteControllerToRemoteControllerOutPutDto(remoteController));
        }
        return remoteControllerDtoList;
    }



    public RemoteControllerOutPutDto getRemoteControllerId (long id) {
        Optional<RemoteController> remoteController = repos.findById(id);
        if (remoteController.isPresent()){
            return Mapper.convertRemoteControllerToRemoteControllerOutPutDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remote controller id found");
        }
    }

    public RemoteControllerInputDto createRemoteController(RemoteControllerInputDto createRemoteControllerDto){
        repos.save(Mapper.fromDtoToToRemoteController(createRemoteControllerDto));
        return createRemoteControllerDto;
    }




    public RemoteControllerOutPutDto updateRemoteController(long id, RemoteControllerInputDto RemoteControllerInputDto){
        Optional<RemoteController> getRemoteController = repos.findById(id);
        if (getRemoteController.isEmpty()){
            throw new RecordNotFoundException("No remote controller found with id");
        } else {
            RemoteController changeRC = getRemoteController.get();
            changeRC.setCompatibleWith(RemoteControllerInputDto.compatibleWith);
            changeRC.setBatteryType(RemoteControllerInputDto.batteryType);
            changeRC.setName(RemoteControllerInputDto.name);
            changeRC.setBrand(RemoteControllerInputDto.brand);
            changeRC.setPrice(RemoteControllerInputDto.price);
            changeRC.setOriginalStock(RemoteControllerInputDto.originalStock);

            RemoteController returnRC = repos.save(changeRC);
            return Mapper.convertRemoteControllerToRemoteControllerOutPutDto(returnRC);
        }
    }


    public void deleteRemoteController(long id) {
        Optional<RemoteController> optionalRemoteController = repos.findById(id);

        if (optionalRemoteController.isPresent()) {
            repos.deleteById(id);
        } else {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        }
    }
}
