-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: recipes
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (21);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipe` (
  `id` int(11) NOT NULL,
  `category` int(11) NOT NULL,
  `directions` varchar(1000) DEFAULT NULL,
  `img` varchar(500) DEFAULT NULL,
  `ingredients` varchar(1000) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,3,'\"1. Slice oni1. Slice onions -- 1/8\"\" thick. 2. Melt butter, place onions in it, saute slowly for 1 1/2 hours in a large soup pot. 3. Add all other ingredients except bouillon, saute over low heat 10 minutes more. \"','https://photos.riverfronttimes.com/wp-content/uploads/2020/04/03-FRENCH-ONION.jpg','\"3 pounds3 pounds (1360 grams) Onions, raw 8 tbsp Butter, unsalted 1.50 tsp Pepper, black 1 tsp, crumbled Bay Leaf 2 tbsp Paprika 8 fl oz White Wine 12 cups Beef Broth 0.75 cup spelt flour 2 tsp Sea Salt (1360 grams) Onions, raw\"','Famous Barr\'s French Onion Soup',0),(3,5,'\"1. Preheat oven to 350° F. Line muffin tins with 12 foil cupcake papers. Place a vanilla wafer in the bottom of each cupcake paper.  2. In mixing bowl, beat cream cheese and fat-free cream cheese until smooth. Add sugar and vanilla and mix well. Add eggs and beat until smooth.  ','https://sparkpeo.hs.llnwd.net/e1/resize/630m620/e2/guid/Mini-Cheesecakes-RECIPE/fdf44cec-936c-4abd-bc71-675fdb5aac2f.jpg','\"12 low-fat vanilla wafers 3 oz. cream cheese, at room temperature 12 oz. fat-free cream cheese, at room temperature 1/2 cup sugar 1/2 teaspoon vanilla 2 eggs cherry pie filling\"','Mini Cheesecakes',0),(5,0,'Combine the first 3 ingredients in a gallon-size ziploc bag, shake it up, and then add the salmon. Allow to marinate in the refrigerator for an hour, turning after half an hour. Pour the salmon and the marinade into a baking dish and bake in a 350 degree oven, covered with foil, for 15 minutes. The salmon is done when it flakes easily at the thickest part. Enjoy! Makes (4) 3 ounce servings. ','https://sparkpeo.hs.llnwd.net/e2/guid/Worlds-Best-(and-Easiest)-Salmon/d6637946-83d1-4cbb-90c2-a40ab6ea8780.jpg','\"1/4 cup pure maple syrup (NOT pancake syrup!) or honey 1/4 cup soy sauce 2-3 cloves minced garlic 12 ounces fresh or thawed salmon\"','World\'s Best (and Easiest) Salmon',5),(8,2,'Preheat the oven or toaster oven to 425 degrees F. Brush both sides of the eggplant with the oil and season with the salt and pepper. Arrange on a baking sheet and bake until browned and almost tender, 6 to 8 minutes, turning once. Spread 1 tablespoon of pasta sauce on each eggplant slice. Top with the shredded cheese. Bake until the cheese melts, 3 to 5 minutes. Serve hot.','https://sparkpeo.hs.llnwd.net/e2/guid/-Mini-Eggplant-Pizzas/f098cccb-7de1-4738-a18a-ab6774ca6325.jpg','\"1 eggplant - 3 inches in diameter, peeled and cut into 4 half-inch thick slices 4 teaspoons olive oil 1/2 teaspoon salt 1/8 teaspoon ground black pepper 1/4 cup pasta sauce 1/2 cup shredded part-skim mozzarella cheese\"','Mini Eggplant Pizzas',1),(9,4,'1. Wash tomatoes and cilantro.\r\n2. Dice tomatoes, onions, chop cilantro, jalapenos, and the optional ingredients (avocado, cucumber)\r\n3. Put ingredients in a bowl.\r\n4. Add salt, garlic, the juice of half a lemon. Mix it up and serve.','https://sparkpeo.hs.llnwd.net/e2/guid/Pico-de-Gallo---Authentic-Mexican-Salsa/23188500-3d62-4362-a3fe-8885b798241e.jpg','3 large diced tomatoes 1 diced medium sized onion 1/4 bunch of cilantro (use more or less depending on your taste) juice of half a lemon 1/2 teaspoon of minced garlic 1 tsp of salt 2 jalapenos (or more if you prefer it hotter)','Pico de Gallo - Authentic Mexican Salsa',1),(10,2,'1. Preheat oven to 425 degrees F.\r\n2. In a shallow pan, toss potatoes and carrots with oil, salt and pepper.\r\n3. Nestle peeled garlic cloves amongst the vegetables and scatter the rosemary on top.\r\n4. Arrange the chicken among the vegetables and bake uncovered for 30 minutes.\r\n5. Meanwhile, stir the mustard and honey together.\r\n6. Remove the pan from the oven. Carefully take the chicken from the pan to another clean plate. Spread the honey-mustard mixture over the chicken.\r\n7. Stir vegetables in the pan, return coated chicken to the pan, and place pan back into the oven. Bake 10-20 minutes, until chicken is cooked and vegetables are tender.','https://sparkpeo.hs.llnwd.net/e4/nw/2/6/l26327171.jpg','1 lb. potatoes, cut into wedges\r\n2 lbs. chicken, rinsed\r\n6 medium carrots, sliced\r\n2 Tablespoons olive oil\r\n1-1/2 Tablespoons honey\r\n3 Tablespoons mustard\r\n1 teaspoon dried rosemary\r\n2 heads garlic, peeled\r\nsalt and pepper to taste','Honey Mustard Roasted Chicken',4),(11,5,'Place all ingredients in blender and process to desired consistency. Makes 3 servings approx 3/4 cup each.','https://sparkpeo.hs.llnwd.net/e2/guid/5-Minute-Berry-Smoothie/d702b10b-cab7-4bbd-92a6-6cf53d2131b6.jpg','1 cup berries any type (I like Kirkland\'s Frozen Mixed Berry Blend)\r\n1 small banana (6\")\r\n1 cup Low Fat Vanilla Yogurt (I used Mountain High which is made with fructose, if you use an artificially sweetened product the calories will be lower)\r\n1/2 cup skim milk or enough as needed to make to a drinking consistency','5-Minute Berry Smoothie',0),(12,5,'CRUST: Spread the sugar cookie dough over a 14-inch pizza pan. Bake in a 375 degree oven for 12 minutes or until lightly golden brown. Cool in the pan.\r\n\r\nTOPPING: Blend the cream cheese with the sugar and vanilla until completely mixed. Spread in a thin layer over the cooled crust.\r\n\r\nFRUIT LAYER: Creatively arrange the fruit in circles while slightly overlapping the slices around the crust.\r\n\r\nGLAZE: Bring the water and preserves to a boil, stirring constantly. Lightly brush this glaze on top on the fruit to preserve the color. Refrigerate until ready to serve.','https://sparkpeo.hs.llnwd.net/e2/guid/Fruit-Pizza-/824e0313-21dd-4a0c-ba52-e7c88b8cff25.jpg','1/2 package of refrigerated sugar cookie dough\r\n8 ounces reduced-fat cream cheese, whipped\r\n1/3 cup powdered sugar\r\n1/2 teaspoon vanilla\r\n1 tablespoon water\r\n1/4 cup apricot preserves\r\nFruit of your choice (sliced bananas, sliced strawberries, sliced kiwi, seedless grapes cut in half, blueberries, melon balls sliced in half)','Fruit Pizza',0),(13,5,'CRUST: Spread the sugar cookie dough over a 14-inch pizza pan. Bake in a 375 degree oven for 12 minutes or until lightly golden brown. Cool in the pan.\r\n\r\nTOPPING: Blend the cream cheese with the sugar and vanilla until completely mixed. Spread in a thin layer over the cooled crust.\r\n\r\nFRUIT LAYER: Creatively arrange the fruit in circles while slightly overlapping the slices around the crust.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Fruit-Pizza-/824e0313-21dd-4a0c-ba52-e7c88b8cff25.jpg','1/2 package of refrigerated sugar cookie dough\r\n8 ounces reduced-fat cream cheese, whipped\r\n1/3 cup powdered sugar\r\n1/2 teaspoon vanilla\r\n1 tablespoon water\r\n1/4 cup apricot preserves\r\nFruit of your choice (sliced bananas, sliced strawberries, sliced kiwi, seedless grapes cut in half, blueberries, melon balls sliced in half)','Fruit Pizza',0),(14,5,'CRUST: Spread the sugar cookie dough over a 14-inch pizza pan. Bake in a 375 degree oven for 12 minutes or until lightly golden brown. Cool in the pan.\r\n\r\nTOPPING: Blend the cream cheese with the sugar and vanilla until completely mixed. Spread in a thin layer over the cooled crust.\r\n\r\nFRUIT LAYER: Creatively arrange the fruit in circles while slightly overlapping the slices around the crust.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Fruit-Pizza-/824e0313-21dd-4a0c-ba52-e7c88b8cff25.jpg','1/2 package of refrigerated sugar cookie dough\r\n8 ounces reduced-fat cream cheese, whipped\r\n1/3 cup powdered sugar\r\n1/2 teaspoon vanilla\r\n1 tablespoon water\r\n1/4 cup apricot preserves\r\nFruit of your choice (sliced bananas, sliced strawberries, sliced kiwi, seedless grapes cut in half, blueberries, melon balls sliced in half)','Fruit Pizza',0),(15,5,'CRUST: Spread the sugar cookie dough over a 14-inch pizza pan. Bake in a 375 degree oven for 12 minutes or until lightly golden brown. Cool in the pan.\r\n\r\nTOPPING: Blend the cream cheese with the sugar and vanilla until completely mixed. Spread in a thin layer over the cooled crust.\r\n\r\nFRUIT LAYER: Creatively arrange the fruit in circles while slightly overlapping the slices around the crust.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Fruit-Pizza-/824e0313-21dd-4a0c-ba52-e7c88b8cff25.jpg','1/2 package of refrigerated sugar cookie dough\r\n8 ounces reduced-fat cream cheese, whipped\r\n1/3 cup powdered sugar\r\n1/2 teaspoon vanilla\r\n1 tablespoon water\r\n1/4 cup apricot preserves\r\nFruit of your choice (sliced bananas, sliced strawberries, sliced kiwi, seedless grapes cut in half, blueberries, melon balls sliced in half)','Fruit Pizza',0),(16,5,'CRUST: Spread the sugar cookie dough over a 14-inch pizza pan. Bake in a 375 degree oven for 12 minutes or until lightly golden brown. Cool in the pan.\r\n\r\nTOPPING: Blend the cream cheese with the sugar and vanilla until completely mixed. Spread in a thin layer over the cooled crust.\r\n\r\nFRUIT LAYER: Creatively arrange the fruit in circles while slightly overlapping the slices around the crust.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Fruit-Pizza-/824e0313-21dd-4a0c-ba52-e7c88b8cff25.jpg','1/2 package of refrigerated sugar cookie dough\r\n8 ounces reduced-fat cream cheese, whipped\r\n1/3 cup powdered sugar\r\n1/2 teaspoon vanilla\r\n1 tablespoon water\r\n1/4 cup apricot preserves\r\nFruit of your choice (sliced bananas, sliced strawberries, sliced kiwi, seedless grapes cut in half, blueberries, melon balls sliced in half)','Fruit Pizza',0),(17,3,'Crumble sausage into a Dutch oven; add onion. Cook and stir over medium heat until meat is no longer pink.\r\n\r\nAdd garlic; cook and stir 2 minutes longer.\r\n\r\nAdd the broth and tomatoes. Bring to a boil.\r\n\r\nStir in tortellini; return to a boil. Reduce heat; simmer, uncovered, for 5-8 minutes or until pasta is tender, stirring occasionally.\r\n\r\nAdd the spinach, basil, pepper and pepper flakes; cook 2-3 minutes longer or until spinach is wilted. Serve with cheese if desired.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Rustic-Italian-Tortellini-Soup/57d3d1d0-0779-4198-a233-ca08a758e6dd.jpg','3 Italian turkey sausage links (4 ounces each), casings removed (I used the \"hot\" sausage version)\r\n1 medium onion, chopped\r\n6 garlic cloves, minced\r\n4 cups reduced-sodium chicken broth\r\n1 can (14-1/2 ounces) diced tomatoes, undrained\r\n1 package (9 ounces) refrigerated cheese tortellini\r\n1 package (6 ounces) fresh baby spinach, coarsely chopped\r\n2-1/4 teaspoons minced fresh basil or 3/4 teaspoon dried basil (or, you can substitute thyme and oregano)\r\n1/4 teaspoon pepper','Rustic Italian Tortellini Soup',4),(18,2,'1. Place chicken in 13x9x2\" glass baking dish.\r\n\r\n2. Mix lemon juice, vinegar, lemon peel, oregano, and onions. Pour over chicken, cover and marinate in refrigerator several hours or overnight, turning occasionally.\r\n\r\n3. Sprinkle with salt, pepper, and paprika.\r\n\r\n4. Cover and bake at 325º F for 30 minutes. Uncover and bake 30 minutes more or until done.\r\n\r\n','https://sparkpeo.hs.llnwd.net/e2/guid/Easy-Lemon-Chicken-/1217b5a4-46f2-4eb0-8ccc-00eb59111056.jpg','1-1/2 lb. chicken breast, skinned and fat removed\r\n3 lemons, juiced and zested, flesh cut into segments\r\n1 Tablespoon vinegar (or balsamic vinegar)\r\n3 teaspoons chopped fresh oregano or 1 teaspoon dried oregano, crushed\r\n1 medium onion, sliced\r\n1/4 teaspoon salt\r\nBlack pepper to taste\r\n1/2 teaspoon paprika','Easy Lemon Chicken',4),(19,5,'Beat egg whites and dash of salt until soft peaks form. Gradually add in sugar while beating until peaks are stiff and glossy.\r\nFold in coconut.\r\nDrop by rounded teaspoon onto greased baking sheet.\r\nBake at 325*F 18-20 minutes until set and very slightly browned.\r\nCenter will still be soft.','https://sparkpeo.hs.llnwd.net/e2/guid/Coconut-Meringue-Cookies/fe015d9c-5250-4e5f-aa81-d5a337cdaaa6.jpg','1-1/2 cups sweetened shredded coconut\r\n2 egg whites\r\n1/4 tsp vanilla extract\r\nDash of salt\r\n2/3 cup granulated sugar','Coconut Meringue Cookies',2),(20,1,'Add all ingredients to blender or food processor and process to desired consistency. Add salt (about 1/2 tsp) to taste. Serve chilled. Enjoy with your favorite chips or tortillas.','https://sparkpeo.hs.llnwd.net/e2/guid/Coach-Nicoles-Fresh-&-Skinny-Guacamole/2d0c18b7-6bf7-45c9-8858-d45734e9fcfa.jpg','2 ripe avocados, peeled and chopped\r\n1/3 medium organic* cucumber, chopped\r\n1/3 medium onion, chopped (I use red onion)\r\n1 garlic clove, minced\r\n1 tsp cumin powder\r\n1 squeeze lemon (about 1-2 Tbsp juice)\r\nSalt to taste (about 1/2 tsp)','Coach Nicole\'s Fresh & Skinny Guacamole',1);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredient`
--

DROP TABLE IF EXISTS `recipe_ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recipe_ingredient` (
  `id` int(11) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `recipe_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_RECIPE_INGREDIENT` (`recipe_id`),
  CONSTRAINT `FK_RECIPE_INGREDIENT` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredient`
--

LOCK TABLES `recipe_ingredient` WRITE;
/*!40000 ALTER TABLE `recipe_ingredient` DISABLE KEYS */;
/*!40000 ALTER TABLE `recipe_ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pw_hash` varchar(255) DEFAULT NULL,
  `user_role` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_recipe`
--

DROP TABLE IF EXISTS `user_recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_recipe` (
  `id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_RECIPE` (`recipe_id`),
  KEY `FK_USER` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_USER_RECIPE` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_recipe`
--

LOCK TABLES `user_recipe` WRITE;
/*!40000 ALTER TABLE `user_recipe` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_recipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-04  9:33:57
