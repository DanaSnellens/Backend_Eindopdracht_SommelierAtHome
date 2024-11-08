-- Insert into clients table
INSERT INTO clients (id, username, first_name, last_name, email, password, profile_picture_url, membership)
VALUES
       (nextval('user_sequence'),'client1', 'Emma', 'Johnson', 'emmajohnson@gmail.com', '$2a$10$9', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 'REGULAR'),
       (nextval('user_sequence'),'client2', 'Liam', 'Williams', 'liamwilliams@gmail.com', '$2a$12$GUjbpLbcg0DGsfTPPKPOIuQZSToS.1RLGPPdxRbSCtSWs.TvECU/G', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 'BASIC'),
       (nextval('user_sequence'),'client3', 'Olivia', 'Martinez', 'oliviamartinez@gmail.com', '$2a$10$b', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 'PREMIUM'),
       (nextval('user_sequence'),'client4', 'Noah', 'Davis', 'noahdavis@gmail.com', '$2a$10$c', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 'REGULAR'),
       (nextval('user_sequence'),'client5', 'Sophia', 'Garcia', 'sophiagarcia@gmail.com', '$2a$10$d', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cloudy-Bay-Sauvignon-Blanc.jpg', 'PREMIUM'),
       (nextval('user_sequence'),'client6', 'Mason', 'Brown', 'masonbrown@gmail.com', '$2a$10$e', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Almaviva-2019.jpg', 'BASIC');

-- Insert into sommeliers table
INSERT INTO sommeliers (id, username, first_name, last_name, email, password, profile_picture_url, sommelier_description, certificates, experience_in_years, curriculum_vitae, specialization)
VALUES
    (nextval('user_sequence'), 'sommelier1', 'Alice', 'Smith', 'alicesmith@gmail.com', '$2a$12$uEmKxBO6nFHPlearWS7Icu6PzbA3O2VEGB9SbuiPjACTfTxJulx7y', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 'Certified sommelier with a passion for wine and food pairing. I have extensive experience in the wine industry and love helping clients discover new wines.', 'Certified Sommelier (CMS)', 10, '2020-2024: Winebar VinVin, 2018-2020: Restaurant The Elephant, 2014-2018: Winebar Lefebre', 'Wine and Food Pairing'),
       (nextval('user_sequence'), 'sommelier2', 'James', 'Anderson', 'jamesanderson@gmail.com', '$2a$10$4', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 'A seasoned sommelier with a focus on Old World wines and a deep understanding of Burgundy and Bordeaux regions. Dedicated to creating memorable wine experiences.', 'Advanced Sommelier (CMS)', 12, '2012-2024: Restaurant La Table, 2008-2012: Winehouse Bar & Grill', 'Old World Wines'),
       (nextval('user_sequence'), 'sommelier3', 'Laura', 'Gonzalez', 'lauragonzalez@gmail.com', '$2a$10$5', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 'Specializing in South American wines, I am passionate about highlighting the uniqueness of wines from Argentina and Chile. Extensive experience in guiding clients through food and wine pairing.', 'Certified Specialist of Wine (CSW)', 8, '2016-2024: The Wine Cellar, 2014-2016: Buenos Aires Bistro', 'South American Wines'),
       (nextval('user_sequence'), 'sommelier4', 'Michael', 'Brown', 'michaelbrown@gmail.com', '$2a$10$6', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 'Expert in fine dining wine service with a strong focus on high-end European wines, including Champagne and Burgundy. Aiming to provide guests with unique and refined wine selections.', 'Diploma in Wine and Spirits (WSET)', 15, '2009-2024: The Grand Hotel, 2005-2009: Château de Ville', 'European Wines'),
       (nextval('user_sequence'), 'sommelier5', 'Sophie', 'Dupont', 'sophiedupont@gmail.com', '$2a$10$7', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cloudy-Bay-Sauvignon-Blanc.jpg', 'Experienced sommelier with a focus on organic and biodynamic wines. Passionate about sustainable viticulture and educating clients on eco-friendly wine choices.', 'Certified Sommelier (CMS), Organic Wine Specialist', 7, '2017-2024: Restaurant Green Vine, 2013-2017: Bistro Naturelle', 'Organic and Biodynamic Wines'),
       (nextval('user_sequence'), 'sommelier6', 'David', 'Nguyen', 'davidnguyen@gmail.com', '$2a$10$8', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Almaviva-2019.jpg', 'With a focus on New World wines, I have years of experience in pairing wines from Australia, New Zealand, and California. I enjoy curating unique wine lists for modern restaurants.', 'Advanced Sommelier (CMS)', 9, '2015-2024: Winehouse 1920, 2010-2015: The Modern Palate', 'New World Wines');

INSERT INTO roles (id, role_name)
VALUES (1, 'CLIENT'),
       (2, 'ADMIN');


insert INTO client_roles(client_id, role_id) VALUES
       (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1);

INSERT INTO sommelier_roles(sommelier_id, role_id) VALUES
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2),
       (11, 2),
       (12, 2);


INSERT INTO wines (id, wine_name, country, region, grape_varietal, producer, wine_style, wine_type, food_pairing, year, price, image_link, image_alt, short_description, long_description)
VALUES (1001,'Chateau Montelena Chardonnay', 'United States', 'California', 'Chardonnay', 'Chateau Montelena', 'White', 'Still', 'Chicken, Pork, Rich Fish (Salmon, Tuna etc), Vegetarian, Poultry', 2016, 50.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 'Chateau Montelena Chardonnay', 'Chateau Montelena Chardonnay is a white wine from California. It is a full-bodied, rich, and creamy wine with flavors of apple, pear, and vanilla.', 'Chateau Montelena Chardonnay is a white wine from California. It is a full-bodied, rich, and creamy wine with flavors of apple, pear, and vanilla.'),
       (1002, 'Domaine de la Romanée-Conti La Tâche', 'France', 'Burgundy', 'Pinot Noir', 'Domaine de la Romanée-Conti', 'Red', 'Still', 'Beef, Game (Deer, Venison), Poultry', 2015, 4200.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 'Domaine de la Romanée-Conti La Tâche', 'A legendary Burgundy Pinot Noir, offering rich complexity with aromas of red fruit, truffle, and spice.', 'A prestigious and rare Burgundy red wine with earthy notes, perfect for game and beef dishes.'),
       (1003, 'Penfolds Grange', 'Australia', 'South Australia', 'Shiraz', 'Penfolds', 'Red', 'Still', 'Beef, Lamb, Spicy Food', 2018, 850.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 'Penfolds Grange', 'Australia’s most iconic Shiraz, offering rich blackberry, plum, and spicy oak.', 'Penfolds Grange is known for its bold, complex flavors of dark fruits, spices, and oak, making it an excellent match for rich red meats.'),
       (1004,'Marchesi Antinori Tignanello', 'Italy', 'Tuscany', 'Sangiovese', 'Marchesi Antinori', 'Red', 'Still', 'Beef, Lamb, Pasta', 2017, 125.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Marchesi-Antinori-Tignanello-2017.jpg', 'Marchesi Antinori Tignanello', 'An iconic Super Tuscan blend of Sangiovese and Cabernet, with bold fruit and spice notes.', 'Tignanello is a bold and structured wine from Tuscany, blending Sangiovese and Cabernet Sauvignon with ripe fruit and savory notes.'),
       (1005,'Cloudy Bay Sauvignon Blanc', 'New Zealand', 'Marlborough', 'Sauvignon Blanc', 'Cloudy Bay', 'White', 'Still', 'Shellfish, Goat Cheese, Salad', 2021, 35.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cloudy-Bay-Sauvignon-Blanc.jpg', 'Cloudy Bay Sauvignon Blanc', 'A crisp and vibrant Sauvignon Blanc with notes of citrus, tropical fruits, and fresh herbs.', 'Cloudy Bay Sauvignon Blanc is known for its zesty citrus and tropical fruit flavors, making it ideal for seafood dishes and light salads.'),
       (1006,'Vega Sicilia Unico', 'Spain', 'Ribera del Duero', 'Tempranillo', 'Vega Sicilia', 'Red', 'Still', 'Beef, Lamb, Game', 2009, 550.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Vega-Sicilia-Unico-2009.jpg', 'Vega Sicilia Unico', 'A legendary Spanish wine with deep complexity, offering dark fruit, spice, and earthy notes.', 'Vega Sicilia Unico is a prestigious Tempranillo blend from Ribera del Duero, with deep layers of flavor perfect for hearty meats.'),
       (1007,'Chateau Margaux', 'France', 'Bordeaux', 'Cabernet Sauvignon', 'Chateau Margaux', 'Red', 'Still', 'Beef, Lamb, Game', 2015, 750.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 'Chateau Margaux', 'A prestigious Bordeaux wine with silky tannins, complex fruit, and elegant structure.', 'Chateau Margaux is a classic Bordeaux blend with flavors of blackcurrant, violet, and earthy notes, ideal for pairing with lamb and beef.'),
       (1008,'Cakebread Cellars Cabernet Sauvignon', 'United States', 'California', 'Cabernet Sauvignon', 'Cakebread Cellars', 'Red', 'Still', 'Beef, Lamb, Poultry', 2018, 75.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cakebread-Cellars-Cabernet-Sauvignon-2018.jpg', 'Cakebread Cellars Cabernet Sauvignon', 'A Napa Valley Cabernet Sauvignon with rich blackberry, cassis, and chocolate flavors.', 'Cakebread Cellars produces a full-bodied Cabernet Sauvignon with ripe berry flavors and smooth tannins, perfect for steak and grilled meats.'),
       (1009,'Gaja Barbaresco', 'Italy', 'Piedmont', 'Nebbiolo', 'Gaja', 'Red', 'Still', 'Beef, Veal, Pasta', 2016, 300.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Gaja-Barbaresco-2016.jpg', 'Gaja Barbaresco', 'An elegant Nebbiolo from Piedmont, with rose, cherry, and earthy aromas.', 'Gaja Barbaresco is a refined Italian red with floral and fruit aromas, balanced acidity, and firm tannins, making it ideal for classic Italian dishes.'),
       (1010,'Chateau d’Yquem', 'France', 'Bordeaux', 'Semillon, Sauvignon Blanc', 'Chateau d’Yquem', 'White', 'Sweet', 'Fruit-based Desserts, Blue Cheese', 2011, 400.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-dYquem-2011.jpg', 'Chateau d’Yquem', 'A world-famous sweet wine with flavors of apricot, honey, and vanilla.', 'Chateau d’Yquem is a legendary sweet wine from Bordeaux, known for its rich sweetness and perfect pairing with desserts and blue cheese.'),
       (1011,'Bollinger La Grande Année', 'France', 'Champagne', 'Pinot Noir, Chardonnay', 'Bollinger', 'Sparkling', 'Champagne', 'Shellfish, Caviar, Poultry', 2012, 180.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Bollinger-La-Grande-Annee-2012.jpg', 'Bollinger La Grande Année', 'A prestigious Champagne with notes of citrus, brioche, and toasted almond.', 'Bollinger La Grande Année is a rich and complex Champagne, ideal for celebrations or pairing with seafood and fine dining.'),
       (1012,'Almaviva', 'Chile', 'Maipo Valley', 'Cabernet Sauvignon, Carmenere', 'Almaviva', 'Red', 'Still', 'Beef, Lamb, Poultry', 2019, 145.00, 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Almaviva-2019.jpg', 'Almaviva', 'A top-tier Chilean red blend offering deep fruit flavors and a smooth finish.', 'Almaviva is a bold and well-balanced red blend, combining Cabernet Sauvignon and Carmenere, making it perfect for grilled meats and robust dishes.');

INSERT INTO recipes (id, recipe_name, course, main_ingredient, other_ingredients,servings, preparation_time, wine_pairing, image_link, image_alt, preparation_short_description, preparation_long_description)
VALUES (2001,'Beef Wellington', 'Main Course', 'Beef', 'Mushrooms, Puff Pastry, Prosciutto, Duxelles, Egg Wash', 4, 120, 'Cabernet Sauvignon, Bordeaux', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 'Beef Wellington', 'A classic British dish featuring beef tenderloin wrapped in puff pastry, perfect for special occasions.', 'Beef Wellington is a luxurious dish that combines tender beef, savory mushrooms, and flaky pastry for a show-stopping meal. It is often served with a rich red wine, such as Cabernet Sauvignon or Bordeaux.'),
       (2002, 'Coq au Vin', 'Main Course', 'Chicken', 'Bacon, Red Wine, Mushrooms, Pearl Onions, Garlic', 4, 150, 'Pinot Noir, Burgundy', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 'Coq au Vin', 'A French classic where chicken is braised in red wine, often served with mashed potatoes or crusty bread.', 'Coq au Vin is a rich, comforting dish made by slow-cooking chicken with bacon, wine, and mushrooms. The depth of the red wine sauce is beautifully balanced by a good Pinot Noir.'),
       (2003,'Spaghetti Carbonara', 'Main Course', 'Pasta', 'Eggs, Pancetta, Parmesan, Black Pepper', 4, 20, 'Chardonnay, Pinot Grigio', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 'Spaghetti Carbonara', 'A quick and creamy Italian pasta dish made with eggs, pancetta, and Parmesan cheese.', 'Spaghetti Carbonara is a comforting Roman pasta dish that’s quick to prepare. The richness of the egg-based sauce pairs well with a crisp white wine, like Chardonnay or Pinot Grigio.'),
       (2004, 'Lamb Shank Tagine', 'Main Course', 'Lamb', 'Apricots, Almonds, Chickpeas, Cumin, Cinnamon', 4, 180, 'Syrah, Zinfandel', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 'Lamb Shank Tagine', 'A Moroccan-inspired dish with slow-cooked lamb and spices, perfect for a hearty dinner.', 'Lamb Shank Tagine is a fragrant, slow-cooked Moroccan dish with tender lamb and a sweet-spicy blend of apricots, almonds, and warm spices. A bold red wine, such as Syrah or Zinfandel, pairs well with its richness.'),
       (2005, 'Salmon en Croute', 'Main Course', 'Salmon', 'Spinach, Puff Pastry, Cream Cheese, Dill', 4, 90, 'Chardonnay, Champagne', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cloudy-Bay-Sauvignon-Blanc.jpg', 'Salmon en Croute', 'Salmon fillets baked in puff pastry with a creamy spinach filling.', 'Salmon en Croute is a delicate and flavorful dish where salmon fillets are wrapped in puff pastry with a spinach and cream cheese filling. Best served with a light, buttery Chardonnay or a sparkling Champagne.'),
       (2006, 'Osso Buco', 'Main Course', 'Veal', 'White Wine, Carrots, Celery, Garlic, Gremolata', 4, 180, 'Barolo, Barbaresco', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Gaja-Barbaresco-2016.jpg', 'Osso Buco', 'A traditional Italian dish of braised veal shanks, often served with risotto or polenta.', 'Osso Buco is a classic Italian comfort dish where veal shanks are slowly braised in white wine and vegetables, served with gremolata. Pair it with a full-bodied Italian red like Barolo or Barbaresco.'),
       (2007, 'Ratatouille', 'Main Course', 'Eggplant', 'Zucchini, Bell Peppers, Tomatoes, Garlic, Olive Oil', 4, 60, 'Rosé, Grenache', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Vega-Sicilia-Unico-2009.jpg', 'Ratatouille', 'A Provençal vegetable dish made with eggplant, zucchini, and bell peppers, perfect for summer.', 'Ratatouille is a flavorful, vegetable-based dish from Provence, ideal for vegetarians. It is served warm or at room temperature and pairs wonderfully with a dry Rosé or a light Grenache.'),
       (2008, 'Duck à l’Orange', 'Main Course', 'Duck', 'Oranges, Grand Marnier, Butter, Thyme, Garlic', 4, 150, 'Pinot Noir, Bordeaux', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 'Duck à l’Orange', 'A French dish featuring roasted duck with a tangy orange sauce.', 'Duck à l’Orange is a French classic where rich, roasted duck is paired with a citrusy, slightly sweet orange sauce. The dish pairs beautifully with a fine Pinot Noir or a light Bordeaux.'),
       (2009, 'Bouillabaisse', 'Main Course', 'Seafood', 'Fish, Shellfish, Fennel, Saffron, Tomatoes', 6, 120, 'Sauvignon Blanc, Chablis', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-dYquem-2011.jpg', 'Bouillabaisse', 'A traditional French seafood stew with fish, shellfish, and aromatic herbs.', 'Bouillabaisse is a traditional Provençal fish stew made with a variety of fish, shellfish, and aromatic herbs like fennel and saffron. The delicate seafood flavors are complemented by a crisp white wine like Sauvignon Blanc or Chablis.'),
       (2010, 'Mushroom Risotto', 'Main Course', 'Arborio Rice', 'Mushrooms, Parmesan, White Wine, Garlic, Thyme', 4, 40, 'Chardonnay, Pinot Noir', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Marchesi-Antinori-Tignanello-2017.jpg', 'Mushroom Risotto', 'A creamy and savory risotto with mushrooms and Parmesan cheese.', 'Mushroom Risotto is a creamy Italian dish made by slowly stirring Arborio rice with mushrooms, Parmesan, and white wine. It pairs well with both light red wines like Pinot Noir and rich whites like Chardonnay.'),
       (2011,'Shrimp Scampi', 'Main Course', 'Shrimp', 'Garlic, White Wine, Lemon, Butter, Parsley', 4, 25, 'Sauvignon Blanc, Pinot Grigio', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Almaviva-2019.jpg', 'Shrimp Scampi', 'A quick and easy shrimp dish with a buttery garlic and lemon sauce.', 'Shrimp Scampi is a quick and flavorful dish where shrimp is cooked in a garlic butter sauce with white wine and lemon. This light and bright dish is best paired with a crisp white wine like Sauvignon Blanc or Pinot Grigio.');


INSERT INTO wineadvicerequests (id, client_id, sommelier_id, dinner_occasion, request_message, recipe_link, min_price_per_bottle, max_price_per_bottle)
    VALUES (3001, 1, 7, 'Anniversary Dinner', 'Looking for a special wine to pair with our anniversary dinner. We are planning to cook Beef Wellington.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 50.00, 100.00),
    (3002,1, 8,'Dinner Party', 'Hosting a dinner party and need wine recommendations for a multi-course meal. We are planning to serve Coq au Vin as the main course.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 100.00, 200.00),
    (3003,2, 9, 'Weeknight Dinner', 'Looking for an affordable wine to pair with a quick and easy pasta dish. We are planning to make Spaghetti Carbonara.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 20.00, 50.00),
    (3004,2, 10,'Family Dinner', 'Need wine suggestions for a family dinner with a hearty lamb dish. We are planning to cook Lamb Shank Tagine.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Marchesi-Antinori-Tignanello-2017.jpg', 50.00, 100.00),
    (3005,3, 11,'Date Night', 'Looking for a romantic wine to pair with a special seafood dish. We are planning to make Salmon en Croute.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Cloudy-Bay-Sauvignon-Blanc.jpg', 50.00, 100.00),
    (3006,3, 12,'Holiday Dinner', 'Need wine recommendations for a festive veal dish. We are planning to cook Osso Buco for the holidays.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Vega-Sicilia-Unico-2009.jpg', 100.00, 200.00),
    (3007,4, 11,'Vegetarian Dinner', 'Looking for wine pairings for a vegetarian dinner with a classic French dish. We are planning to make Ratatouille.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Margaux-2015.jpg', 20.00, 50.00),
    (3008,4, 10,'Special Occasion', 'Need wine suggestions for a special duck dish. We are planning to cook Duck à l’Orange for a celebration.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 50.00, 100.00),
    (3009,5, 9,'Seafood Dinner', 'Looking for wine pairings for a traditional French seafood stew. We are planning to make Bouillabaisse.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Domaine-de-la-Romanee-Conti-La-Tache.jpg', 50.00, 100.00),
    (3010,5,8,'Italian Dinner', 'Need wine recommendations for a creamy mushroom risotto. We are planning to make Mushroom Risotto for dinner.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Penfolds-Grange-2018.jpg', 20.00, 50.00),
    (3011,6,7,'Quick Dinner', 'Looking for an easy wine pairing for a quick shrimp dish. We are planning to make Shrimp Scampi for dinner.', 'https://www.wine.com/product/images/w_600,h_600,c_fit,q_auto:good,fl_progressive/Chateau-Montelena-Chardonnay-2016.jpg', 20.00, 50.00);


INSERT INTO wineadvices (id, wineadvicerequest_id, personal_message, advice_explanation)
    VALUES (4001, 3001,'Happy Anniversary! Chateau Montelena Chardonnay is a classic choice for special occasions, with its rich and creamy flavors that will complement the Beef Wellington perfectly.', 'Chateau Montelena Chardonnay is a full-bodied white wine with rich flavors of apple, pear, and vanilla, making it an excellent match for the bold flavors of Beef Wellington.'),
    (4002, 3002,'For your dinner party, Domaine de la Romanée-Conti La Tâche is a prestigious Burgundy Pinot Noir that will impress your guests with its complexity and elegance.', 'Domaine de la Romanée-Conti La Tâche is a legendary Burgundy Pinot Noir with rich complexity and aromas of red fruit, truffle, and spice, making it a perfect pairing for the rich flavors of Coq au Vin.'),
    (4003, 3003,'For your weeknight dinner, Penfolds Grange is an iconic Australian Shiraz that offers bold blackberry and plum flavors, ideal for a quick and easy pasta dish like Spaghetti Carbonara.', 'Penfolds Grange is known for its bold flavors of dark fruits and spices, making it a great match for the creamy and savory flavors of Spaghetti Carbonara.'),
    (4004,3004,'For your family dinner, Marchesi Antinori Tignanello is an iconic Super Tuscan blend with bold fruit and spice notes that will complement the hearty lamb flavors of Lamb Shank Tagine.', 'Marchesi Antinori Tignanello is a bold and structured wine with ripe fruit and savory notes, making it an excellent match for the rich and fragrant Lamb Shank Tagine.'),
    (4005,3005,'For your date night, Cloudy Bay Sauvignon Blanc is a crisp and vibrant white wine with zesty citrus and tropical fruit flavors that will enhance the delicate flavors of Salmon en Croute.', 'Cloudy Bay Sauvignon Blanc is known for its zesty citrus and tropical fruit flavors, making it an ideal pairing for the light and buttery Salmon en Croute.'),
    (4006,3006,'For your holiday dinner, Vega Sicilia Unico is a legendary Spanish wine with deep complexity and flavors of dark fruit, spice, and earthy notes that will elevate the festive flavors of Osso Buco.', 'Vega Sicilia Unico is a prestigious Tempranillo blend with deep layers of flavor, making it a perfect match for the rich and comforting Osso Buco.'),
    (4007,3007,'For your vegetarian dinner, Chateau Margaux is a prestigious Bordeaux wine with silky tannins, complex fruit, and elegant structure that will complement the classic flavors of Ratatouille.', 'Chateau Margaux is a classic Bordeaux blend with flavors of blackcurrant, violet, and earthy notes, making it an ideal pairing for the rich and flavorful Ratatouille.'),
    (4008,3008,'For your special occasion, Chateau Montelena Chardonnay is a classic choice with its rich and creamy flavors that will enhance the tangy orange sauce of Duck à l’Orange.', 'Chateau Montelena Chardonnay is a full-bodied white wine with rich flavors of apple, pear, and vanilla, making it an excellent match for the citrusy and slightly sweet Duck à l’Orange.'),
    (4009,3009,'For your seafood dinner, Domaine de la Romanée-Conti La Tâche is a prestigious Burgundy Pinot Noir that will elevate the delicate flavors of Bouillabaisse with its complexity and elegance.', 'Domaine de la Romanée-Conti La Tâche is a legendary Burgundy Pinot Noir with rich complexity and aromas of red fruit, truffle, and spice, making it a perfect pairing for the delicate seafood flavors of Bouillabaisse.'),
    (4010,3010,'For your Italian dinner, Penfolds Grange is an iconic Australian Shiraz that offers bold blackberry and plum flavors, ideal for the creamy and savory Mushroom Risotto.', 'Penfolds Grange is known for its bold flavors of dark fruits and spices, making it a great match for the rich and creamy Mushroom Risotto.'),
    (4011,3011,'For your quick dinner, Chateau Montelena Chardonnay is a classic choice with its rich and creamy flavors that will complement the buttery garlic sauce of Shrimp Scampi.', 'Chateau Montelena Chardonnay is a full-bodied white wine with rich flavors of apple, pear, and vanilla, making it an excellent match for the light and bright Shrimp Scampi.');

INSERT INTO wineadvices_wines (wineadvice_id, wine_id)
VALUES (4001, 1001),
       (4001, 1007),
       (4002, 1004),
       (4002, 1009),
       (4003, 1001),
       (4003, 1005),
       (4003, 1008),
       (4004, 1002),
       (4004, 1007),
       (4005, 1003),
       (4005, 1007),
       (4005, 1012),
       (4006, 1011),
       (4006, 1012),
       (4007, 1001),
       (4007, 1004),
       (4007, 1008),
       (4008, 1001),
       (4008, 1007),
       (4009, 1002),
       (4009, 1007),
       (4010, 1001),
       (4010, 1004),
       (4010, 1008),
       (4011, 1001),
       (4011, 1005);

INSERT INTO recipes_wines (recipe_id, wine_id)
VALUES (2001, 1001),
       (2001, 1007),
       (2002, 1004),
       (2002, 1009),
       (2003, 1001),
       (2003, 1005),
       (2003, 1008),
       (2004, 1002),
       (2004, 1007),
       (2005, 1003),
       (2005, 1007),
       (2005, 1012),
       (2006, 1011),
       (2006, 1012),
       (2007, 1001),
       (2007, 1004),
       (2007, 1008),
       (2008, 1001),
       (2008, 1007),
       (2009, 1002),
       (2009, 1007),
       (2010, 1001),
       (2010, 1004),
       (2010, 1008),
       (2011, 1001),
       (2011, 1005);


