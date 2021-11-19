-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema withplay
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema withplay
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `withplay` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `withplay` ;

-- -----------------------------------------------------
-- Table `withplay`.`picture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`picture` (
  `id` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`sport_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`sport_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `birth` DATE NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `picture_id` VARCHAR(255) NULL DEFAULT NULL,
  `user_authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_picture1_idx` (`picture_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_picture1`
    FOREIGN KEY (`picture_id`)
    REFERENCES `withplay`.`picture` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 103
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`team` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `leader_id` BIGINT NOT NULL,
  `picture_id` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `member_count` INT NOT NULL,
  `max_count` INT NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `recruitment_state` BIT(1) NOT NULL,
  `membership_fee` BIT(1) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `sport_category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_user1_idx` (`leader_id` ASC) VISIBLE,
  INDEX `fk_team_picture1_idx` (`picture_id` ASC) VISIBLE,
  INDEX `fk_team_sport_category1_idx` (`sport_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_team_picture1`
    FOREIGN KEY (`picture_id`)
    REFERENCES `withplay`.`picture` (`id`),
  CONSTRAINT `fk_team_sport_category1`
    FOREIGN KEY (`sport_category_id`)
    REFERENCES `withplay`.`sport_category` (`id`),
  CONSTRAINT `fk_team_user1`
    FOREIGN KEY (`leader_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 69
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`calendar` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `team_id` BIGINT NOT NULL,
  `writer_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `schedule` TIMESTAMP NOT NULL,
  `year` INT NOT NULL,
  `month` INT NOT NULL,
  `day` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_calendar_team1_idx` (`team_id` ASC) VISIBLE,
  INDEX `fk_calendar_user1_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_calendar_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `withplay`.`team` (`id`),
  CONSTRAINT `fk_calendar_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '		';


-- -----------------------------------------------------
-- Table `withplay`.`chat_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`chat_room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `pub` BIGINT NOT NULL,
  `sub` BIGINT NOT NULL,
  `latest_message_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `pub_idx` (`pub` ASC) VISIBLE,
  INDEX `sub_idx` (`sub` ASC) VISIBLE,
  CONSTRAINT `pub`
    FOREIGN KEY (`pub`)
    REFERENCES `withplay`.`user` (`id`),
  CONSTRAINT `sub`
    FOREIGN KEY (`sub`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`chat_message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`chat_message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `room_id` BIGINT NOT NULL,
  `sender` BIGINT NOT NULL,
  `message` VARCHAR(9999) NOT NULL,
  `time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_id_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `withplay`.`chat_room` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 187
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`confirmation_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`confirmation_token` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `expiration_date` DATETIME NULL DEFAULT NULL,
  `expired` BIT(1) NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `withplay`.`email`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`email` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `expiration_date` DATETIME NULL DEFAULT NULL,
  `expired` BIT(1) NULL DEFAULT NULL,
  `create_date` DATETIME NULL DEFAULT NULL,
  `last_modified_date` DATETIME NULL DEFAULT NULL,
  `code` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`match_board_post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`match_board_post` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `writer_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `recruited` BIT(1) NOT NULL,
  `place` VARCHAR(45) NULL DEFAULT NULL,
  `recruitment_count` VARCHAR(45) NULL DEFAULT NULL,
  `team_id` BIGINT NULL DEFAULT NULL,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `sport_category_id` BIGINT NOT NULL,
  `match_board_category` VARCHAR(10) NOT NULL,
  `playing_time` DATE NULL DEFAULT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_matching_board_user1_idx` (`writer_id` ASC) VISIBLE,
  INDEX `fk_matching_board_sport_category1_idx` (`sport_category_id` ASC) VISIBLE,
  INDEX `fk_match_board_post_team_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_match_board_post_sport_category1`
    FOREIGN KEY (`sport_category_id`)
    REFERENCES `withplay`.`sport_category` (`id`),
  CONSTRAINT `fk_match_board_post_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `withplay`.`team` (`id`),
  CONSTRAINT `fk_match_board_post_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 43
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`match_board_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`match_board_comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `match_board_post_id` BIGINT NOT NULL,
  `writer_id` BIGINT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `modified` BIT(1) NOT NULL,
  `depth` INT NOT NULL,
  `parent_id` BIGINT NOT NULL,
  `reply_count` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_matching_board_comment_matching_board1_idx` (`match_board_post_id` ASC) VISIBLE,
  INDEX `fk_matching_board_comment_user1_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_match_board_comment_match_board_post1`
    FOREIGN KEY (`match_board_post_id`)
    REFERENCES `withplay`.`match_board_post` (`id`),
  CONSTRAINT `fk_match_board_comment_match_board_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 117
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`notification` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `receiver_id` BIGINT NOT NULL,
  `post_id` BIGINT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `checked` BIT(1) NOT NULL,
  `create_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_notification_receiver_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `fk_notification_receiver`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `withplay`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 93
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`refresh_token` (
  `user_id` VARCHAR(45) NOT NULL,
  `value` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`request_join`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`request_join` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `team_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `create_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_request_join_team_idx` (`team_id` ASC) VISIBLE,
  INDEX `fk_request_join_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_request_join_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `withplay`.`team` (`id`),
  CONSTRAINT `fk_request_join_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 137
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `withplay`.`team_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`team_board` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `writer_id` BIGINT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `team_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_board_user1_idx` (`writer_id` ASC) VISIBLE,
  INDEX `fk_team_board_team1_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_team_board_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `withplay`.`team` (`id`),
  CONSTRAINT `fk_team_board_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `withplay`.`team_board_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`team_board_comment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `team_board_id` BIGINT NOT NULL,
  `writer_id` BIGINT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `modify_date` DATETIME NOT NULL,
  `is_modified` BIT(1) NOT NULL,
  `parent_id` BIGINT NOT NULL,
  `depth` INT NOT NULL,
  `reply_count` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_team_board_comment_team_board1_idx` (`team_board_id` ASC) VISIBLE,
  INDEX `fk_team_board_comment_user1_idx` (`writer_id` ASC) VISIBLE,
  CONSTRAINT `fk_team_board_comment_team_board1`
    FOREIGN KEY (`team_board_id`)
    REFERENCES `withplay`.`team_board` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_team_board_comment_user1`
    FOREIGN KEY (`writer_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 68
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `withplay`.`user_chat_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`user_chat_room` (
  `user_id` BIGINT NOT NULL,
  `chat_room_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `chat_room_id`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`user_interest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`user_interest` (
  `user_id` BIGINT NOT NULL,
  `sport_category_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `sport_category_id`),
  INDEX `fk_interest_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_interest_sport_category1_idx` (`sport_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_interest_sport_category`
    FOREIGN KEY (`sport_category_id`)
    REFERENCES `withplay`.`sport_category` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_user_interest_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `withplay`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `withplay`.`user_team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `withplay`.`user_team` (
  `team_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  `register_date` DATETIME NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`team_id`, `user_id`),
  INDEX `fk_user_team_team_idx` (`team_id` ASC) VISIBLE,
  INDEX `fk_user_team_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_team_team`
    FOREIGN KEY (`team_id`)
    REFERENCES `withplay`.`team` (`id`),
  CONSTRAINT `fk_user_team_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `withplay`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
