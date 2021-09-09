package uz.pdp.app_info_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_info_system.entity.Lavozim;
import uz.pdp.app_info_system.payload.ApiResponse;
import uz.pdp.app_info_system.payload.LavozimDto;
import uz.pdp.app_info_system.repository.LavozimRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimService {
    @Autowired
    LavozimRepository lavozimRepository;

    public ApiResponse addLavozim(LavozimDto lavozimDto) {
        Optional<Lavozim> optionalLavozim = lavozimRepository.findByName(lavozimDto.getName());
        if (optionalLavozim.isPresent())
            return new ApiResponse("Bunday nomli lavozim mavjud",false);
        Lavozim lavozim=new Lavozim();
        lavozim.setName(lavozimDto.getName());
        lavozim.setDescription(lavozimDto.getDescription());
        lavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepository.save(lavozim);
        return new ApiResponse("Lavozim saqlandi",true);
    }

    public ApiResponse editLavozim(LavozimDto lavozimDto, Long id) {
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(id);
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Lavzim topilmadi", false);
        Lavozim lavozim = optionalLavozim.get();
        lavozim.setName(lavozimDto.getName());
        lavozim.setDescription(lavozimDto.getDescription());
        lavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepository.save(lavozim);
        return new ApiResponse("Lavzim tahrirlandi", true);
    }

    public List<Lavozim> getLavozim() {
        return lavozimRepository.findAll();
    }


    public ApiResponse deleteLavozim(Long id) {
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(id);
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Lavzim topilmadi", false);
        lavozimRepository.deleteById(id);
        return new ApiResponse("Lavzim o'chirildi", true);
    }
}
