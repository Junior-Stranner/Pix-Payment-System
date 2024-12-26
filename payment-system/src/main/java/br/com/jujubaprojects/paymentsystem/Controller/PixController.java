/*package br.com.jujubaprojects.paymentsystem.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jujubaprojects.paymentsystem.Service.PixService;
import br.com.jujubaprojects.paymentsystem.dto.PixChargeRequest;

@RestController
@RequestMapping("/api/v1/pix")
public class PixController {


    @Autowired
    private PixService pixService;

    @GetMapping
    public ResponseEntity createPix(){
        JSONObject  response = this.pixService.pixCreateEVP();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.toString());
        
    }

      @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixChargeRequest pixChargeRequest){
        JSONObject response = this.pixService.pixCreateCharge(pixChargeRequest);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }
    
}*/
