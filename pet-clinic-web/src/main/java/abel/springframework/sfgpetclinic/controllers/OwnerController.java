package abel.springframework.sfgpetclinic.controllers;

import abel.springframework.sfgpetclinic.model.Owner;
import abel.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String OWNERS_OWNER_DETAILS_VIEW = "owners/ownerDetails";
    private static final String OWNERS_FIND_OWNERS_VIEW = "owners/findOwners";
    private static final String OWNERS_OWNERS_LIST_VIEW = "owners/ownersList";
    private static final String REDIRECT_OWNERS_VIEW = "redirect:/owners/";
    private static final String CREATE_OR_UPDATE_OWNER_VIEW = "owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", new Owner()); //TODO: Implement Builder pattern
        return OWNERS_FIND_OWNERS_VIEW;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if(results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return OWNERS_FIND_OWNERS_VIEW;
        } else if (results.size() == 1) {
            owner = results.iterator().next();
            return REDIRECT_OWNERS_VIEW + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return OWNERS_OWNERS_LIST_VIEW;
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView(OWNERS_OWNER_DETAILS_VIEW);
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", new Owner()); //TODO: Implement Builder pattern
        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            return REDIRECT_OWNERS_VIEW + ownerService.save(owner).getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if(result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            owner.setId(ownerId);
            return REDIRECT_OWNERS_VIEW + ownerService.save(owner).getId();
        }
    }
}
