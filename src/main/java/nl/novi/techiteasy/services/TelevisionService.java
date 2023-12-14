package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.TelevisionOutPutDto;
import nl.novi.techiteasy.dtos.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repository.RemoteControllerRepository;
import nl.novi.techiteasy.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository repo;
    private final RemoteControllerRepository remotrepo;

    public TelevisionService(TelevisionRepository repos, RemoteControllerRepository remotrepos){this.repo = repos;this.remotrepo = remotrepos;}


    public TelevisionInputDto createTelevision(TelevisionInputDto televisionInputDto) {
     repo.save(fromDtoToTelevision(televisionInputDto));
        return televisionInputDto;
    }

    public List<TelevisionOutPutDto> GetAllTelevision() {
        List<Television> televisionList = repo.findAll();
        List<TelevisionOutPutDto> televisionOutPutDtoList = new ArrayList<>();

        for(Television television : televisionList) {
            televisionOutPutDtoList.add(fromTelevisionToDto(television));
        }
        return televisionOutPutDtoList;
    }

    public TelevisionOutPutDto getTelevisionById(Long id) {

       Optional<Television> tv = repo.findById(id);

        if(tv.isPresent()){
            return fromTelevisionToDto(tv.get());
        }
        else {
            throw new RecordNotFoundException("no tv founded");
        }
    }

    public TelevisionOutPutDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Optional<Television> optionalTelevision = repo.findById(id);

        if (optionalTelevision.isPresent()) {
            Television existingTelevision = optionalTelevision.get();
            existingTelevision.setName(televisionInputDto.name);
            existingTelevision.setPrice(televisionInputDto.price);

            repo.save(existingTelevision);

            return fromTelevisionToDto(existingTelevision);
        } else {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        }
    }
    public void deleteTelevision(Long id) {
        Optional<Television> optionalTelevision = repo.findById(id);

        if (optionalTelevision.isPresent()) {
            repo.deleteById(id);
        } else {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        }
    }
    public void assignRemoteControllerToTelevision(Long televisionId, Long remoteControllerId) {
        Television television = repo.findById(televisionId)
                .orElseThrow(() -> new RecordNotFoundException("Television with id " + televisionId + " not found"));

        RemoteController remoteController = remotrepo.findById(remoteControllerId)
                .orElseThrow(() -> new RecordNotFoundException("RemoteController with id " + remoteControllerId + " not found"));

        television.setRemoteController(remoteController);
        remoteController.setTelevision(television);

        repo.save(television);
        remotrepo.save(remoteController);
    }


    public TelevisionOutPutDto fromTelevisionToDto(Television television){
        TelevisionOutPutDto dto = new TelevisionOutPutDto();
        dto.id = television.getId();
        dto.name = television.getName();
        dto.price = television.getPrice();
        return dto;
    }

    public Television fromDtoToTelevision(TelevisionInputDto dto){
        Television television = new Television();
        television.setName(dto.name);
        television.setPrice(dto.price);
        return  television;
    }

}
