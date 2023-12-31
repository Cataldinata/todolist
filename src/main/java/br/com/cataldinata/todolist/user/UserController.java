package br.com.cataldinata.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* Modificadores de acesso Java
 * public
 * private
 * protected
*/

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;


    @PostMapping ("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
      var user =  this.userRepository.findByUsername(userModel.getUsername());

      if(user != null){
        //Mensagem de erro
        //Status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
      }



      var userCreated = this.userRepository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
