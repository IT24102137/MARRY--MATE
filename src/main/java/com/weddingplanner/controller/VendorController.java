package com.weddingplanner.controller;

import com.weddingplanner.model.CatererVendor;
import com.weddingplanner.model.FloristVendor;
import com.weddingplanner.model.PhotographerVendor;
import com.weddingplanner.model.Vendor;
import com.weddingplanner.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "vendorRegistration";
    }

    // Handle registration
    @PostMapping("/register")
    public String registerVendor(@ModelAttribute Vendor vendor,
                                 @RequestParam String vendorType,
                                 @RequestParam(required = false) String photographyStyle,
                                 @RequestParam(required = false) Integer yearsOfExperience,
                                 @RequestParam(required = false) Boolean providesVideography,
                                 @RequestParam(required = false) String cuisineType,
                                 @RequestParam(required = false) Boolean providesWaiterService,
                                 @RequestParam(required = false) Integer maxCapacity,
                                 @RequestParam(required = false) String specialization,
                                 @RequestParam(required = false) Boolean providesSetupService,
                                 @RequestParam(required = false) Boolean providesConsultation,
                                 Model model) {

        Vendor newVendor;
        switch (vendorType) {
            case "Photographer":
                PhotographerVendor pv = new PhotographerVendor();
                pv.setName(vendor.getName());
                pv.setEmail(vendor.getEmail());
                pv.setPhone(vendor.getPhone());
                pv.setLocation(vendor.getLocation());
                pv.setDescription(vendor.getDescription());
                pv.setPriceRange(vendor.getPriceRange());
                pv.setPhotographyStyle(photographyStyle);
                pv.setYearsOfExperience(yearsOfExperience != null ? yearsOfExperience : 0);
                pv.setProvidesVideography(providesVideography != null && providesVideography);
                newVendor = pv;
                break;

            case "Caterer":
                CatererVendor cv = new CatererVendor();
                cv.setName(vendor.getName());
                cv.setEmail(vendor.getEmail());
                cv.setPhone(vendor.getPhone());
                cv.setLocation(vendor.getLocation());
                cv.setDescription(vendor.getDescription());
                cv.setPriceRange(vendor.getPriceRange());
                cv.setCuisineType(cuisineType);
                cv.setProvidesWaiterService(providesWaiterService != null && providesWaiterService);
                cv.setMaxCapacity(maxCapacity != null ? maxCapacity : 0);
                newVendor = cv;
                break;

            case "Florist":
                FloristVendor fv = new FloristVendor();
                fv.setName(vendor.getName());
                fv.setEmail(vendor.getEmail());
                fv.setPhone(vendor.getPhone());
                fv.setLocation(vendor.getLocation());
                fv.setDescription(vendor.getDescription());
                fv.setPriceRange(vendor.getPriceRange());
                fv.setSpecialization(specialization);
                fv.setProvidesSetupService(providesSetupService != null && providesSetupService);
                fv.setProvidesConsultation(providesConsultation != null && providesConsultation);
                newVendor = fv;
                break;

            default:
                vendor.setVendorType(vendorType);
                newVendor = vendor;
                break;
        }

        if (vendorService.createVendor(newVendor)) {
            return "redirect:/vendors/list";
        } else {
            model.addAttribute("error", "Failed to register vendor. Please try again.");
            model.addAttribute("vendor", new Vendor());
            return "vendorRegistration";
        }
    }

    // List all vendors
    @GetMapping("/list")
    public String listVendors(Model model) {
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "vendorListing";
    }

    // View single profile
    @GetMapping("/profile/{id}")
    public String viewVendorProfile(@PathVariable String id, Model model) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            return "redirect:/vendors/list";
        }
        model.addAttribute("vendor", vendor);
        return "vendorProfile";
    }

    // Show edit form (reuses vendorProfile.jsp with form visible)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            return "redirect:/vendors/list";
        }
        model.addAttribute("vendor", vendor);
        return "vendorProfile";
    }

    // Handle update
    @PostMapping("/update")
    public String updateVendor(@ModelAttribute Vendor vendor,
                               @RequestParam String vendorType,
                               @RequestParam(required = false) String photographyStyle,
                               @RequestParam(required = false) Integer yearsOfExperience,
                               @RequestParam(required = false) Boolean providesVideography,
                               @RequestParam(required = false) String cuisineType,
                               @RequestParam(required = false) Boolean providesWaiterService,
                               @RequestParam(required = false) Integer maxCapacity,
                               @RequestParam(required = false) String specialization,
                               @RequestParam(required = false) Boolean providesSetupService,
                               @RequestParam(required = false) Boolean providesConsultation,
                               Model model) {

        Vendor existing = vendorService.getVendorById(vendor.getId());
        if (existing == null) {
            model.addAttribute("error", "Vendor not found");
            return "redirect:/vendors/list";
        }

        Vendor updated;
        switch (vendorType) {
            case "Photographer":
                PhotographerVendor p = existing instanceof PhotographerVendor
                        ? (PhotographerVendor) existing
                        : new PhotographerVendor();
                p.setId(vendor.getId());
                p.setName(vendor.getName());
                p.setEmail(vendor.getEmail());
                p.setPhone(vendor.getPhone());
                p.setLocation(vendor.getLocation());
                p.setDescription(vendor.getDescription());
                p.setPriceRange(vendor.getPriceRange());
                p.setPhotographyStyle(photographyStyle);
                p.setYearsOfExperience(yearsOfExperience != null ? yearsOfExperience : 0);
                p.setProvidesVideography(providesVideography != null && providesVideography);
                updated = p;
                break;

            case "Caterer":
                CatererVendor c = existing instanceof CatererVendor
                        ? (CatererVendor) existing
                        : new CatererVendor();
                c.setId(vendor.getId());
                c.setName(vendor.getName());
                c.setEmail(vendor.getEmail());
                c.setPhone(vendor.getPhone());
                c.setLocation(vendor.getLocation());
                c.setDescription(vendor.getDescription());
                c.setPriceRange(vendor.getPriceRange());
                c.setCuisineType(cuisineType);
                c.setProvidesWaiterService(providesWaiterService != null && providesWaiterService);
                c.setMaxCapacity(maxCapacity != null ? maxCapacity : 0);
                updated = c;
                break;

            case "Florist":
                FloristVendor f = existing instanceof FloristVendor
                        ? (FloristVendor) existing
                        : new FloristVendor();
                f.setId(vendor.getId());
                f.setName(vendor.getName());
                f.setEmail(vendor.getEmail());
                f.setPhone(vendor.getPhone());
                f.setLocation(vendor.getLocation());
                f.setDescription(vendor.getDescription());
                f.setPriceRange(vendor.getPriceRange());
                f.setSpecialization(specialization);
                f.setProvidesSetupService(providesSetupService != null && providesSetupService);
                f.setProvidesConsultation(providesConsultation != null && providesConsultation);
                updated = f;
                break;

            default:
                vendor.setVendorType(vendorType);
                updated = vendor;
                break;
        }

        if (vendorService.updateVendor(updated)) {
            return "redirect:/vendors/profile/" + vendor.getId();
        } else {
            model.addAttribute("error", "Failed to update vendor. Please try again.");
            model.addAttribute("vendor", existing);
            return "vendorProfile";
        }
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteVendor(@PathVariable String id) {
        vendorService.deleteVendor(id);
        return "redirect:/vendors/list";
    }

    // Search by type
    @GetMapping("/search/type")
    public String searchByType(@RequestParam String type, Model model) {
        model.addAttribute("vendors", vendorService.searchVendorsByType(type));
        model.addAttribute("searchCriteria", "Vendor Type: " + type);
        return "vendorListing";
    }

    // Search by location
    @GetMapping("/search/location")
    public String searchByLocation(@RequestParam String location, Model model) {
        model.addAttribute("vendors", vendorService.searchVendorsByLocation(location));
        model.addAttribute("searchCriteria", "Location: " + location);
        return "vendorListing";
    }
}
