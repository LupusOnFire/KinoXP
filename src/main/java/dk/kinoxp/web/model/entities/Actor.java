package dk.kinoxp.web.model.entities;

import javax.persistence.*;

    @Entity
    @Table(name = "actor")
    public class Actor {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "actor_id")
        private int id;

        @Column(name = "name")
        private String name;

        public Actor() {
        }

        public Actor(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
