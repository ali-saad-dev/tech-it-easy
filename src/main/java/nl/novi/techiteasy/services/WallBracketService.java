package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.WallBracketInputDto;
import nl.novi.techiteasy.dtos.WallBracketOutPutDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.helpper.Mapper;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repository.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository repos;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.repos = wallBracketRepository;
    }



    public WallBracketInputDto createWallBracket(WallBracketInputDto wallBracketInputDto) {
        repos.save(Mapper.fromDtoToWallBracket(wallBracketInputDto));
        return wallBracketInputDto;
    }

    public List<WallBracketOutPutDto> getAllWallBrackets() {
        List<WallBracket> wallBracketList = repos.findAll();
        List<WallBracketOutPutDto> dtos = new ArrayList<>();
        for (WallBracket wb : wallBracketList) {
            dtos.add(Mapper.fromWallBracketToDto(wb));
        }
        return dtos;
    }

    public WallBracketOutPutDto getWallBracketId(long id) {
        Optional<WallBracket> wallBracket = repos.findById(id);
        if(wallBracket.isPresent()) {
            return Mapper.fromWallBracketToDto((wallBracket.get()));
        } else {
            throw new RecordNotFoundException("No wallbracket found");
        }
    }

    public WallBracketOutPutDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        Optional<WallBracket> wallBracketId = repos.findById(id);

        if (wallBracketId.isEmpty()) {
            throw new RecordNotFoundException("wallbracket with id " + id + " not found");
        } else {
            WallBracket changeWallBracket = wallBracketId.get();

            changeWallBracket.setSize(wallBracketInputDto.size);
            changeWallBracket.setAdjustable(wallBracketInputDto.adjustable);
            changeWallBracket.setName(wallBracketInputDto.name);
            changeWallBracket.setPrice(wallBracketInputDto.price);
            WallBracket  updatedWallBracket = repos.save(changeWallBracket);
            return Mapper.fromWallBracketToDto(updatedWallBracket);
        }
    }

    public void deleteWallBracket ( long id){
        Optional<WallBracket> optionalWallBracket = repos.findById(id);

        if (optionalWallBracket.isPresent()) {
            repos.deleteById(id);
        } else {
            throw new RecordNotFoundException("wallbracket with id " + id + " not found");
        }
    }
}
