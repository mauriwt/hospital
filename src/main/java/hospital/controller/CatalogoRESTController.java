package hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hospital.model.Catalogo;
import hospital.repository.CatalogoRepo;
import hospital.utilities.ErrorService;
import hospital.utilities.HttpResponseService;

@RestController
@RequestMapping(value="/catalogos")
@CrossOrigin
public class CatalogoRESTController {
  
  @Autowired
  private CatalogoRepo catalogoRepo;

  
  //@PreAuthorize("#oauth2.hasScope('base.scope.action')")
  @RequestMapping(method=RequestMethod.GET)
  public List<Catalogo> getCatalogo()
  {
    return this.catalogoRepo.findAll();
  }
  
  @RequestMapping(value="/tipos/{tipo}", method=RequestMethod.GET)
  public List<Catalogo> getCatalogoByTipo(@PathVariable("tipo") String tipo)
  {
    return this.catalogoRepo.getCatalogoByTipo(tipo);
  }
  
  //@PreAuthorize("#oauth2.hasScope('base.scope.action')")
  @RequestMapping(value="{id}" ,method=RequestMethod.GET)
  public Catalogo getOneCatalogo(@PathVariable("id") int id)
  {
    return this.catalogoRepo.findOne(id);
  }
  
  
  @RequestMapping(method=RequestMethod.POST)
  public ResponseEntity<?> insertCatalogo(@RequestBody @Valid Catalogo catalogo, Errors errors)
  {
    try {
      if(errors.hasErrors())
        throw new Exception(ErrorService.validacion(errors.getAllErrors()));
        return HttpResponseService.responseOK(catalogoRepo.save(catalogo));
    } catch (Exception e) { 
      e.printStackTrace();
      return HttpResponseService.responseInternalError(new ErrorService(e.getMessage(), "POST Catalogo.insertCatalogo", e));
    }
  }
  
  @RequestMapping(value="{id}" ,method=RequestMethod.PUT)
  public ResponseEntity<?> updateCatalogo(@PathVariable("id") int id, @RequestBody @Valid Catalogo catalogo, Errors errors)
  {
    try {
      if(errors.hasErrors())
        throw new Exception(ErrorService.validacion(errors.getAllErrors()));
      
      if(!this.verificarExistencia(id))
        return HttpResponseService.responseBadRequest("El registro no existe.");
      
      return HttpResponseService.responseOK(catalogo);
    } catch (Exception e) {
      e.printStackTrace();
      return HttpResponseService.responseInternalError(new ErrorService(e.getMessage(), "PUT catalogo.updateCatalogo", e));
    }
  }
  
  @RequestMapping(value="{id}" ,method=RequestMethod.DELETE)
  public ResponseEntity<?> deleteCatalogo(@PathVariable("id") int id)
  {
    try {
      
      if(!this.verificarExistencia(id))
        return HttpResponseService.responseBadRequest("El registro no existe.");
      
      this.catalogoRepo.delete(id);
      return HttpResponseService.responseOK();
    } catch (Exception e) {
      e.printStackTrace();
      return HttpResponseService.responseInternalError(new ErrorService(e.getMessage(), "DELETE catalogo.deleteCatalogo", e));
    }
  }
  
  private boolean verificarExistencia(int id){
    return this.catalogoRepo.findOne(id) != null;
  }
  
}
