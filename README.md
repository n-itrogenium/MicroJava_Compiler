# Kompajler za programski jezik _MicroJava_

Cilj projektnog zadatka je realizacija kompajlera za programski jezik _MicroJava_ (skraćeno MJ). Kompajler omogućava prevođenje sintaksno i semantički ispravnih MJ programa u MJ bajtkod, koji se izvršava na virtuelnoj mašini za MJ. Programski prevodilac za MJ ima četiri osnovne funkcionalnosti: leksičku analizu,
sintaksnu analizu, semantičku analizu i generisanje koda.  
  
**Leksički analizator** prepoznaje jezičke lekseme i vrati skup tokena izdvojenih iz izvornog koda, koji se dalje razmatraju u okviru sintaksne analize. Ukoliko se tokom leksičke analize detektuje leksička greška, na izlazu se ispisuje odgovarajuća poruka.  
  
**Sintaksni analizator** ima zadatak da utvrdi da li izdvojeni tokeni iz izvornog koda programa mogu da formiraju gramatički ispravne sentence. Nakon parsiranja sintaksno ispravnih MJ programa, korisnik se obaveštava o uspešnosti parsiranja. Ukoliko izvorni kod ima sintaksne greške, one se adekvatno ispisuju, vrši se oporavak i parsiranje se nastavlja. 
  
**Semantički analizator** se formira na osnovu apstraktnog sintaksnog stabla koje je nastalo kao rezultat sintaksne analize. Semantička analiza se sprovodi implementacijom metoda za posećivanje čvorova apstraktnog sintaksnog stabla. Stablo je formirano na osnovu gramatike implementirane u prethodnoj fazi. Ukoliko izvorni kod ima semantičke greške, vrši se obaveštavanje korisnika.  
  
**Generator koda** prevodi sintaksno i semantički ispravne programe u izvršni oblik za odabrano izvršno okruženje MJ virtuelne mašine. Generisanje koda se implementira na sličan način kao i semantička analiza, implementacijom metoda koje posećuju čvorove.  

## Prevođenje _MicroJava_ koda
Prevođenje MJ koda podrazumeva vršenje navedene četiri faze, koje rezultuju objektnom datotekom sa MJ bajtkodom. Ovo se postiže pokretanjem `test.rs.ac.bg.etf.pp1.MJParserTest` klase, kojoj se zadaju argumenti u formatu `<ulazni MJ kod> <izlazna objektna datoteka>`.

## Pokretanje _MicroJava_ koda
Pokretanje MJ koda je moguće nakon što se generiše odgovarajuća objektna datoteka, koja se zadaje kao argument `Run` klase _MJ Runtime_ biblioteke. 

