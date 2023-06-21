INSERT INTO category
    (id, name, description)
VALUES
    (1, 'Elektronika', 'AGD, RTV, PC'),
    (2, 'Meble', 'Meble dla każdego domu');
INSERT INTO offer
    (id, title, description, img_url, price, category_id )
VALUES
    (1, 'Telewizor', 'Super telewizor o przekątnej 55 cali', 'https://a.allegroimg.com/original/1e7727/b39ae5924cb29e2ea5b41ebc8462', 1299, 1),
    (2, 'Kino domowe', 'Wypasione kino domowe firmy Sony, gra tak, że można robić festyn', 'https://thumbs.img-sprzedajemy.pl/1000x901c/12/e7/41/kino-domowe-sony-pozostale-katowice-sprzedam-516650177.jpg', 699, 1),
    (3, 'Stół drewniany', 'Idealny stół drewniany dla rodziny, 6 krzeseł gratis', 'https://meble-donar.pl/wp-content/uploads/2019/06/Donar-Meble-2018_19_217.jpg', 2699, 2),
    (4, 'Regał na książki', 'Uporządkuj swoje książki z dizajnerskim regałem', 'https://selsey.pl/public/upload/sellasist_cache/thumb_large_9f885aa0b1d2bf6eb45665fa3946cef8.jpg', 1540, 2);



