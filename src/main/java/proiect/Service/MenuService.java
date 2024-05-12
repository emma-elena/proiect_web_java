package proiect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.Entity.MenuEntity;
import proiect.Repository.MenuRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;

    public MenuEntity create(MenuEntity menu){
        repository.save(menu);
        return menu;
    }
}
