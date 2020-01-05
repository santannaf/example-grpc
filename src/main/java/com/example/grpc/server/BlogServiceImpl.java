package com.example.grpc.server;

import com.example.grpc.gateway.BlogEntity;
import com.proto.blog.Blog;
import com.proto.blog.BlogServiceGrpc;
import com.proto.blog.CreateBlogRequest;
import com.proto.blog.CreateBlogResponse;
import io.grpc.stub.StreamObserver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {


        System.out.println("Receive request create blog");


        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("blog");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Blog blog = request.getBlog();

        entityManager.getTransaction().begin();

        entityManager.persist(BlogEntity.builder()
                .tittle(blog.getTitle())
                .message(blog.getMessage())
                .author(blog.getAuthor())
                .build());

        entityManager.getTransaction().commit();

        CreateBlogResponse response = CreateBlogResponse.newBuilder()
                .setBlog(blog.toBuilder().setId(blog.getId())
                        .build())
                .build();

        entityManager.close();
        entityManagerFactory.close();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
