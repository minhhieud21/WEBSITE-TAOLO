package com.example.JavaSpring.controllers;


import com.example.JavaSpring.models.BlogModel;
import com.example.JavaSpring.models.ResponseObject;
import com.example.JavaSpring.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/blog") //localhost:8080/api/v1/blog
@CrossOrigin(origins ="http://localhost:4200")
public class BlogController {

    @Autowired
    BlogService blogService;

    public BlogController(BlogService blogService) {
    }
    @GetMapping("")
    Page<BlogModel> getAllBlog(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size){
        // 4 items/page
        Pageable paging = PageRequest.of(page,size);
        model.addAttribute("product",blogService.getAllBlog(paging));
        return blogService.getAllBlog(paging);
    }
//    // GET by id: localhost:8080/api/v1/blog/:id
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> getBlogById(@PathVariable("id") Long id){
//        Optional<BlogModel> check = blogService.getBlogById(id);
//        return check.isPresent() ?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,check)
//                ):
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject(false,"")
//                );
//    }
//    @PostMapping("/add")
//    void addNewBlog(@RequestBody BlogModel newBlog){
//        blogService.saveBlog(newBlog);
//    }
//    // DETELE: localhost:8080/api/v1/blog/:id
//    // Detele prouct
//
//
//    @GetMapping("/search") //localhost:8080/api/v1/blog/search?title=?
//    List<BlogModel> searchBlog(@RequestParam(required = false) String title){
//        //System.out.println(title);
//        return blogService.getBlogByName(title);
//    }
//    @PutMapping("/{id}")
//    void updateBlog(@RequestBody BlogModel newBlog,@PathVariable Long id){
//        blogService.updateBlog(id, newBlog);
//    }
//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteBlog(@PathVariable Long id){
//        Optional<BlogModel> check = blogService.getBlogById(id);
//         if(check.isPresent() ){
//             blogService.deleteBlog(id);
//             return ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject(true,"")
//                );}
//         else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                     new ResponseObject(false,"")
//             );
//         }
//    }
}
