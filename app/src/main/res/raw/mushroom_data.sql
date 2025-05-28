CREATE TABLE IF NOT EXISTS mushroom_info (
  name TEXT,
  name_pl TEXT,
  description TEXT,
  is_edible TEXT
);

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Agaricus bisporus', 'Pieczarka dwuzarodnikowa', 'Kapelusz 5–10 cm, początkowo półkolisty, później wypłaszczony, biały lub brązowy z drobnymi łuskami. Blaszki wolne, różowe u młodych, ciemnobrązowe u dojrzałych. Trzon 4–6 cm z pierścieniem. Saprotrof, występuje na łąkach i przy oborniku.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita fulva', 'Muchomor rdzawobrązowy', 'Kapelusz 30–80 mm szerokości, początkowo stożkowaty, później wypukły, koloru od białego do ochrowobrązowego. Miąższ wodnisty, biały, kruchy, o słodkawym smaku i łagodnym zapachu. Rośnie w lasach liściastych i mieszanych od czerwca do października.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita rubescens', 'Muchomor czerwieniejący', 'Trzon cylindryczny, u podstawy bulwiasty, powierzchnia gładka lub kosmkowata, z wyraźnym, białym i prążkowanym pierścieniem. Miąższ biały, po przekrojeniu czerwieniejący, o słabym zapachu i słodkawym smaku. Owocnikuje w lasach liściastych i mieszanych.', 'tak po ugotowaniu lub usmażeniu');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Boletus fibrillosus', 'Borowik klinowotrzonowy', 'Kapelusz do 170 mm szerokości, barwy od beżowej do ciemnobrązowej, o pomarszczonej lub drobnowłóknistej teksturze. Rurki żółte, miąższ biały do ochrowego, nie zmienia barwy po przekrojeniu. Trzon żółtawy u góry, brązowy u podstawy, z siateczkowaniem.', 'tak, ale o gorszym smaku niż B. edulis');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cantharellus formosus', '–', 'Kapelusz średniej wielkości, żółtawo-pomarańczowy do łososiowego, z wiekiem z brązowawymi tonami. Fałszywe blaszki wybrzuszone, zbiegające, często z różowawym odcieniem; trzon smukły, miąższ o przyjemnym owocowym zapachu. Występuje pod iglakami od jesieni do zimy.', 'tak, ceniony przez zbieraczy i rynek handlowy');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius torminosus', 'Mleczaj wełnianka', 'Kapelusz 3–8 cm, biały z włóknistymi strefami, owłosiony; blaszki zbiegające, mleczko białe, o paląco ostrym smaku; rośnie w lasach liściastych i iglastych od lata do jesieni.', 'trujący');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius xanthogalactus', '–', 'Kapelusz 3–11 cm, różowocynamonowy; trzonek 2–6 cm, białożółtawy; po uszkodzeniu wypływa białe mleczko, które szybko żółknie; występuje pod sosnami na zachodnim wybrzeżu USA; smak nieprzyjemny.', 'nieznana');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Neoboletus erythropus', 'Borowik ceglastopory', 'Kapelusz 5–15 cm, od beżowego do ciemnobrązowego; rury żółte w młodości, szybko ceglastopomarańczowe i siniejące po uszkodzeniu; trzon żółty z czerwonymi kropkami; rośnie pod świerkami na kwaśnych glebach.', 'tak, po obróbce termicznej');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula claroflava', 'Gołąbek jasnożółty', 'Kapelusz 3–7 cm jasnożółty, wypukły, później lekko spłaszczony; blaszki zbiegające, białawe do kremowych; trzonek biały; miąższ biały, słodkawy; rośnie na torfowiskach i w lasach brzozowo-sosnowych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus spraguei', '–', 'Kapelusz 5–12 cm czerwonawy, pokryty drobnymi, ciemniejszymi łuskami; rurki żółte, słabo zbiegające; trzonek żółtawy z resztkami osłony; mykoryzuje z sosnami; po usunięciu śluzowatej skórki jadalny.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita jacksonii', 'Muchomor smukły', 'Kapelusz 5–12 cm, jaskrawoczerwony z pomarańczowym odcieniem; blaszki wolne, kremowe; trzon biały z luźnym, zwiewnym pierścieniem; mykoryzuje z dębami i sosnami.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Butyriboletus regius', 'Borowik królewski', 'Kapelusz 5–15 cm różowoczerwony, z wiekiem łososiowy; rury początkowo żółte, potem oliwkowe, pory czerwonawobrązowe; trzon żółtawy z siateczką; mykoryzuje z drzewami liściastymi.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cortinarius iodes', 'Mleczaj różowoblaszkowy', 'Kapelusz 2–6 cm fioletowoniebieski, lepki, z drobnymi ciemniejszymi plamkami; blaszki gęste, fiołkowe; trzon smukły, lejkowaty; rośnie w lasach liściastych.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius controversus', '–', 'Kapelusz 4–12 cm szarawy, z wgłębieniem i żeberkami na brzegu; blaszki zbiegające, kremowe; mleczko białe, łagodne, nie zmienia barwy; występuje pod brzozą i dębem.', 'tak, po wymoczeniu');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius deterrimus', 'Mleczaj świerkowy', 'Kapelusz 4–10 cm pomarańczowy, w środku nieco zagłębiony; mleczko pomarańczowe, zieleniejące po kontakcie z powietrzem; blaszki zbiegające; rośnie pod świerkami.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius glyciosmus', 'Mleczaj kokosowy', 'Kapelusz 2–7 cm szer., szary lawendowy, gęste, zbiegające blaszki. Mleczko białe o zapachu kokosa, mykoryzuje z brzozą.', 'tak, jednak o ostrym smaku');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Pseudoboletus parasiticus', 'Borowik pasożytniczy', 'Kapelusz do 6 cm, żółtobrązowy. Trzon 3–7 cm wysoki; rośnie na guzowcach Scleroderma citrinum.', 'tak, ale niewskazany ze względu na trujący żywiciel');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Retiboletus griseus', '–', 'Kapelusz szaro-brązowy. Trzon siatkowany, ciemniejący przy dotyku, miąższ biały, lekko różowiejący; występuje w lasach dębowo-orzechowych Ameryki Płn.', 'nieznana');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula olivacea', 'Gołąbek oliwkowy', 'Kapelusz 8–16 cm, oliwkowy, z wiekiem czerwonobrązowiejący. Blaszki kremowe; trzon różowawy; smak łagodny, orzechowy.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma magnivelare', '', 'Kapelusz 5–20 cm biały z rdzawymi plamami. Trzon 4–15 cm; rośnie symbiotycznie z sosnami i ma intensywny zapach metylocynamonianu.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita flavoconia', 'Muchomor żółtoplamkowy', 'Kapelusz pomarańczowożółty z żółtymi plamkami; blaszki wolne, białe do kremowych; trzon z żółtawym pierścieniem; rośnie w lasach liściastych i mieszanych, mykoryzuje z jodłą.', 'nieznana');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cantharellus cinnabarinus', 'Pieprznik czerwonawy', 'Kapelusz pomarańczowoczerwony do czerwonego, początkowo wypukły, później lejkowaty; fałszywe listewki zbiegające; miąższ żółtawopomarańczowy o owocowym zapachu.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cookeina tricholoma', '–', 'brak danych', 'nieznana');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cortinarius violaceus', 'Zasłonak fioletowy', 'Kapelusz fioletowoniebieski, śluzowaty; blaszki gęste, fiołkowe; rośnie w lasach liściastych i mieszanych; smak gorzki.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius psammicola', 'Mleczaj piaszczysty', 'Kapelusz kremowobiały z oliwkowym odcieniem; blaszki zbiegające; mleczko białe, niewybarwiające; rośnie pod sosnami na glebach piaszczystych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula aurea', 'Gołąbek złotawy', 'Kapelusz 3–9(13) cm, początkowo wypukły, potem wypłaszczony, o barwie żółtozłotej do ochrowej; blaszki białawokremowe; trzon biały; mykoryzuje z dębami i bukami w lasach liściastych na glebach wapiennych, owocniki od lipca do września.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula nigricans', 'Gołąbek czarniawy', 'Kapelusz 5–15 cm, biały z wiekiem brunatniejący do czarnego; blaszki białe, z czasem ciemniejące; trzon biały, stary ciemnobieżny; występuje w lasach liściastych.', 'tak, o mizernym smaku');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula violeipes', 'Gołąbek fiołkowonogi', 'Kapelusz 4–8 cm, purpurowofioletowy, z wiekiem nieco blaknący; blaszki białawe do kremowych; trzon biały z fioletowym odcieniem u nasady; rośnie w lasach liściastych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma saponaceum', 'Purchawka mydlana', 'Kapelusz 5–15 cm oliwkowozielonkawy z odcieniem fioletu, lepki; blaszki białawe, ząbkowane; trzon pełny, biały z fiołkowym odcieniem; wydziela mydlany zapach; spotykana w lasach różnych typów.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Xerocomus subtomentosus', 'Podgrzybek aksamitny', 'Kapelusz 3–7 cm oliwkowobrązowy, aksamitny; rury żółte, po uszkodzeniu nie zmieniające barwy; trzon żółtawy; rośnie pod drzewami liściastymi i iglastymi.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita onusta', 'Muchomor szyszkowaty', 'Kapelusz 5–12 cm, biały z kremowymi pozostałościami zasnówki; blaszki wolne, białe; trzon z pierścieniem i resztkami całkowitej zasłony; mykoryzuje z drzewami liściastymi i iglastymi.', 'nieznana');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Aureoboletus projectellus', 'Borowik amerykański', 'Kapelusz do 20 cm jasnobrązowy, suchy; rury żółte, nie przebarwiające się; trzon do 15 cm żółtobrązowy, z głębokimi żłobieniami; miąższ biały, smak lekko kwaśny; rośnie w Ameryce Płn.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Clavulina rugosa', 'Goździeńczyk pomarszczony', 'Owocnik 4–9 cm wys., początkowo pałkowaty, pomarszczony, białawy do ochrowego; rozgałęziony, o sprężystej, kruchawej tkance; saprotrof w lasach liściastych i iglastych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cortinarius praestans', 'Zasłonak wystawny', 'Kapelusz 5–13 cm, brązowy, włóknisty; blaszki najpierw ochrowe, potem ciemnobrązowe; trzon masywny, pokryty delikatną zasnówką; występuje w lasach liściastych i mieszanych.', 'tak, o niskiej wartości kulinarnej');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cortinarius traganus', 'Zasłonak wonny', 'Kapelusz 4–10 cm fioletowobrązowy, śluzowaty; blaszki fioletowe przechodzące w rdzawo-brązowe; trzon z resztkami zasnówki; silny, nieprzyjemny zapach; rośnie w lasach iglastych.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Hygrocybe cantharellus', 'Wilgotnica lejkowata', 'Kapelusz 6–20 mm szerokości, początkowo kopulasty, potem wypukły, czerwono-pomarańczowy z żółtawą podstawą; blaszki gęste, zbiegające, białe do żółtawych; rośnie na gnijących kłodach i pniach drzew.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius deliciosus', 'Mleczaj rydz', 'Kapelusz 5–15 cm szerokości, najpierw wypukły, później zlejkowato wgłębiony, pomarańczowy z koncentrycznymi pierścieniami; mleczko pomarańczowe, zieleniące po uszkodzeniu; blaszki przyrośnięte; rośnie pod sosnami.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus bovinus', 'Maślak sitarz', 'Kapelusz 2–8 cm szerokości żółto-brązowy, śluzowaty; rurki żółte, szerokie; trzon wąski, podobny barwą do kapelusza; mykoryzuje z sosnami; zalecany do suszenia lub na wywary ze względu na wodnistą konsystencję.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus lakei', 'Maślak daglezjowy', 'Kapelusz 5–15 cm suchy, czerwono-brązowy, łuskowaty; pory żółte, przy nadgryzieniu brązowiejące; trzon żółtawy z czerwonymi prążkami; mykoryzuje z daglezją; jadalny, choć tylko młode okazy bez skórki.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma vaccinum', 'Gąska krowia', 'Kapelusz 2,5–8 cm szerokości, czerwonobrązowy, włóknisty z łuskami; blaszki kremowe, gęste; trzon włóknisty, pusty u dołu; rośnie w symbiozie z sosną i świerkiem; uważany za jadalny, ale o niskiej wartości kulinarnej.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita flavella', 'Brak polskiej nazwy', 'Ma wypukły, cytrynowo-żółty kapelusz o średnicy do 9 cm. Może być również żółto-pomarańczowy z gęstymi, bladożółtymi blaszkami. Trzon jest żółtawo-biały, centralny, o wysokości 9 cm, lekko bulwiasty, zamknięty w pochwę. Pierścień jest żółtawo-biały, rozszerzony i błoniasty. Występuje w Nowej Południowej Walii i Queensland w Australii.', 'nieznane'),
('Amanita virosa', 'Muchomor jadowity', 'Całkowicie biały, z pierścieniem na trzonie i workowatą pochwą u podstawy. Kapelusz początkowo stożkowy z podwiniętymi brzegami, później półkulisty i spłaszczony o średnicy do 12 cm. Ma charakterystyczny garb; jest biały, choć środek może być kremowy. Blaszki są gęste, wolne i białe. Cienki trzon ma wysokość do 15 cm, z wiszącym, rowkowanym pierścieniem. Zarodniki są białe, prawie kuliste i długości 7–10 μm.', 'nie (śmiertelnie trujący)'),
('Cantharellus subalbidus', 'Biały pieprznik', 'Podobny wyglądem do innych pieprzników, z wyjątkiem kremowo-białego koloru i pomarańczowego przebarwienia przy uszkodzeniu. Jest grzybem mikoryzowym występującym w zachodniej części Ameryki Północnej.', 'tak'),
('Cortinarius caperatus', 'Zasłonak kołpakowaty', 'Kapelusz o kolorze ochry, o średnicy do 10 cm, ma włóknistą powierzchnię. Blaszki w kolorze gliny są przytwierdzone do trzonu pod kapeluszem, a trzon jest białawy z białawym pierścieniem. Miąższ ma łagodny zapach i smak. Występuje w lasach iglastych i bukowych, a także na wrzosowiskach późnym latem i jesienią.', 'tak'),
('Cortinarius collinitus', 'Zasłoniak śluzowaty', 'Kapelusz o średnicy 3–9 cm, wypukły do płaskiego, z lepką, żelatynową powierzchnią w wilgotnych warunkach. Młode kapelusze są wypukłe, później prawie płaskie, ale zwykle zachowują szeroki garb. Kapelusze są od średniobrązowych do ciemnobrązowych. Blaszki są przyrośnięte, gęste i bladofioletowe lub blade, z czasem stają się rdzawobrązowe. Trzon ma 6–12 cm długości i 1–1,5 cm grubości.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Gymnopilus junonius', 'Łysak wspaniały', 'Kapelusz 6-15 cm, początkowo półkulisty, później rozpostarty, żółtoochrowy do brązowopomarańczowego, pokryty brązowymi łuskami. Blaszki początkowo kremowe, później rdzawobrązowe. Trzon 8-20 cm z pierścieniem, jasnożółty, ku podstawie ciemniejący. Miąższ żółty, twardy, gorzki w smaku. Saprotrof, rośnie w kępach na pniach i próchniejącym drewnie drzew liściastych i iglastych, szczególnie brzozy, sosny i dębu. Sezon od lipca do października.', 'nie'),
('Lactarius resimus', 'Biały grzyb mleczny', 'Kapelusz początkowo płasko wypukły, później lejkowaty o średnicy do 18-20 cm. Powierzchnia gładka, lekko wilgotna, mleczno-biała, czasem z żółtawym odcieniem. Miąższ dość zwarty, biały, o przyjemnym owocowym aromacie. Blaszki gęste, bladożółte. Zwykle występuje w grupach. Znany także jako surowy, mokry lub pravsky grzyb mleczny.', 'warunkowo jadalny'),
('Russula cyanoxantha', 'Gołąbek niebiesko-żółty', 'Kapelusz o średnicy 5-16 cm, fioletowy, liliowy, niebieski lub zielony, u młodych okazów półkulisty, później rozłożony lub lekko wgłębiony. Krawędzie często wygięte do wewnątrz i popękane. Trzon 5-13 cm, biały lub szarawy, czasem z fioletowym odcieniem. Miąższ biały lub żółtawy, bardzo kruchy. Surowy smakuje gorzko i ostro, ma zapach miodu, owoców lub musztardy. Występuje od końca czerwca do początku września na glebach kwaśnych, szczególnie w pobliżu buku, dębu i świerku.', 'tak'),
('Russula xerampelina', 'Gołąbek śledziowy', 'W niektórych źródłach uznawany za odmianę gołąbka cukrówki. Charakterystyczny jest zapach śledzi wydzielany przez surowy miąższ.', 'tak'),
('Suillus americanus', 'Maślak amerykański', 'Brak informacji w dostępnych wynikach wyszukiwania.', 'brak danych');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Agaricus campestris', 'Pieczarka polna, pieczarka łąkowa', 'Kapelusz o średnicy 3-10 cm, początkowo półkulisty, później wypukły, na koniec rozpostarty, biały, z wiekiem brązowiejący. Blaszki wolne, najpierw różowe, później czerwonawo-brązowe, na końcu czarne. Trzon cylindryczny, biały, 50-80 × 10-25 mm, z delikatnym, zanikającym pierścieniem. Miąższ biały, lekko czerwieniejący przy dotyku. Występuje na urodzajnych glebach, łąkach, często licznie. Sezon: maj-czerwiec i sierpień-październik.', 'tak'),
('Amanita caesarea', 'Muchomor cesarski', 'Wyróżnia się żółtymi do pomarańczowych blaszkami (nie białymi jak u większości muchomorów) oraz pomarańczową nóżką. Brak łatek na kapeluszu, ma wyraźne prążkowanie kapelusza, brak bulwy. Wyrasta ze środka zawiązka (primordium). We Francji znany jako "oronge - pomarańcza". Uznawany za smaczny grzyb, dopuszczony w niektórych państwach do masowego obrotu.', 'tak'),
('Amanita farinosa', 'Muchomor mączny', 'Kapelusz o średnicy 3-6 cm, początkowo wypukły, później płaski, szarobrązowy do oliwkowobrązowego, pokryty białawymi resztkami osłony przypominającymi mąkę. Blaszki białe, gęste. Trzon 4-8 cm, białawy, z delikatnym pierścieniem, który szybko zanika. Podstawa trzonu z workowatą pochwą. Występuje w lasach liściastych i mieszanych.', 'warunkowo jadalny'),
('Amanita silvicola', 'Muchomor leśny', 'Kapelusz o średnicy 5-12 cm, początkowo wypukły, później płaski, białawy do bladożółtego, często z resztkami osłony w formie łatek. Blaszki białe, gęste. Trzon 6-15 cm, białawy, z wyraźnym, zwisającym pierścieniem. Podstawa trzonu bulwiasta z workowatą pochwą. Występuje w lasach iglastych i mieszanych, szczególnie pod sosnami.', 'nie'),
('Armillaria tabescens', 'Opieńka bezpierścieniowa', 'Kapelusz o średnicy 3-8 cm, wypukły do płaskiego, miodowo-brązowy do ciemnobrązowego, pokryty drobnymi łuskami. Blaszki początkowo białawe, później kremowe do brązowawych, zbiegające na trzon. Trzon 5-12 cm, zwężający się ku podstawie, bez pierścienia (w przeciwieństwie do innych opieńek). Rośnie kępkami na martwym drewnie lub u podstawy żywych drzew liściastych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Aureoboletus mirabilis', 'Borowik amerykański, Złotoborowik wspaniały', 'Kapelusz osiąga średnicę od 5 do 15 cm, początkowo półkolisty, później spłaszczony. Ma kolor od złoto-brązowego po czerwono-brązowy, z lekkim połyskiem. Trzon smukły, wydłużony, o wysokości od 8 do 20 cm, pokryty drobną, żółtawą siateczką. Pod kapeluszem znajdują się rurki, początkowo intensywnie żółte, z wiekiem oliwkowe. Preferuje lasy iglaste, rośnie w towarzystwie świerków i jodeł na kwaśnych glebach. Pojawia się od czerwca do października.', 'tak'),
('Boletus aereus', 'Borowik brązowy (Prawdziwek brązowy)', 'Kapelusz o średnicy 5-25 cm, początkowo półkulisty, później wypukły, ciemnobrązowy do czarnobrązowego. Powierzchnia sucha, czasem delikatnie zamszowa. Rurki białawe, później żółtozielone, nie sinieją po uszkodzeniu. Trzon pękaty, brązowy, z delikatną siateczką w górnej części. Miąższ biały, nie zmienia barwy po przekrojeniu. Występuje w lasach liściastych, szczególnie pod dębami i bukami, preferuje cieplejsze regiony.', 'tak'),
('Cortinarius camphoratus', 'Zasłonak odrażający', 'Grzyb z intensywnymi fioletowymi blaszkami, o zdecydowanie jaśniejszym kapeluszu i trzonie. Jest oślizły w dotyku i specyficznie cuchnący, stąd nazwa. Gatunek rzadki.', 'nie (trujący)'),
('Lactarius subpurpureus', 'Mleczaj czerwieniejący', 'Kapelusz o średnicy 5-12 cm, początkowo wypukły, później lejkowaty, ceglastoczerwony do winnobrązowego. Po uszkodzeniu wydziela czerwono-purpurowy sok mleczny. Blaszki zbiegające, czerwonawe z purpurowymi plamami. Trzon cylindryczny, czerwonawy, często z ciemniejszymi plamkami. Występuje pod drzewami iglastymi, szczególnie pod jodłami i świerkami.', 'nie'),
('Rubroboletus satanas', 'Krwistoborowik szatański (dawniej Borowik szatański)', 'Kapelusz pokryty matową, często popękaną skórką w odcieniach białawym, żółtawym, popielatym lub beżowym, która po naciśnięciu przyjmuje kolor brązowawo-ochrowy. Charakterystyczne są ulokowane na spodniej stronie kapelusza rurki, początkowo żółtawe, z czasem nabierające czerwonawego koloru. Uszkodzone rurki po pewnym czasie sinieją.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Agaricus arvensis', 'Pieczarka polna', 'Pieczarka polna, znana także jako "horse mushroom", to grzyb z dużym, białawym kapeluszem do 15 cm średnicy, który często żółknie na powierzchni. Posiada dobrze rozwinięty pierścień z charakterystycznymi ząbkami na spodniej stronie. Miąższ i skórka żółkną po uszkodzeniu. Ma przyjemny anyżkowy zapach. Występuje na łąkach, polach i w parkach, na glebie bogatej w składniki odżywcze.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Agaricus sylvaticus', 'Pieczarka leśna', 'Pieczarka leśna, znana również jako pieczarka obrączkowana, to jadalny grzyb często spotykany w grupach w lasach iglastych. Kapelusz początkowo kopulasty z czerwono-brązowymi łuskami na jasnobrązowym tle, gęstszymi w kierunku środka. Miąższ biały, cienki i jędrny, czerwieniejący, a później brązowiejący po uszkodzeniu. Występuje w lasach iglastych od wczesnego lata lub od września do listopada.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Armillaria mellea', 'Opieńka miodowa', 'Opieńka miodowa to grzyb podstawkowy z rodzaju Armillaria. Jest patogenem roślin powodującym zgniliznę korzeni Armillaria u wielu gatunków roślin. Wytwarza owocniki u podstawy zainfekowanych drzew. Objawy infekcji widoczne są w koronach zainfekowanych drzew jako odbarwione liście, zmniejszony wzrost, zamieranie gałęzi i śmierć. Grzyb jest jadalny, choć niektórzy mogą go nie tolerować. Szeroko rozpowszechniony w strefach umiarkowanych półkuli północnej.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Armillaria solidipes', 'Opieńka ciemna', 'Armillaria solidipes (synonim A. ostoyae) to patogeniczny gatunek grzyba z rodziny obrzękowatych (Physalacriaceae). Ma kremowo-brązowe zabarwienie, wyraźne łuski na kapeluszu i dobrze rozwinięty pierścień. Grzybnia rozprzestrzenia się głównie pod ziemią, tworząc czarne sznury grzybniowe. Owocniki pojawiają się jesienią. Ze względu na niską konkurencję o ziemię i składniki odżywcze, grzyb ten może osiągać ogromne rozmiary - okaz w północno-wschodnim Oregonie uważany jest za największy żywy organizm na Ziemi pod względem masy, powierzchni i objętości.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cantharellus cibarius', 'Pieprznik jadalny', 'Cantharellus cibarius, znany również jako kurka lub pieprznik jadalny, to grzyb o lejkowatym kształcie, owocowym aromacie przypominającym morele i brzoskwiniowej barwie miąższu. Kapelusz o średnicy 2-6 cm, falisty i nieregularny, z pomarszczoną, żyłkowaną spodnią stroną. Trzon cylindryczny, często zakrzywiony, w tym samym kolorze co kapelusz. Rośnie w lasach liściastych i iglastych, szczególnie w towarzystwie dębów. Uważany za cenny gatunek jadalny.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Clitocybe odora', 'Lejkówka anyżkowa', 'Clitocybe odora, znana jako grzyb anyżowy, ma charakterystyczny niebiesko-zielony kapelusz o średnicy 3-8 cm, który z wiekiem blednie. Wyróżnia się intensywnym zapachem anyżu. Kapelusz początkowo jest wypukły, później lejkowaty z falistym brzegiem. Blaszki są bladsze od kapelusza, zbiegające. Trzon ma 4-6 cm wysokości, z białawym, delikatnym meszkiem u podstawy. Występuje w lasach liściastych i iglastych.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Cortinarius semisanguineus', 'Zasłonak czerwonawy', 'Cortinarius semisanguineus, znany również jako "Poison Dye Cort", to średniej wielkości grzyb z oliwkowo-brązowym do ochrowego kapeluszem, który jest początkowo wypukły, później spłaszczony. Jego najbardziej charakterystyczną cechą są jaskrawoczerwone blaszki, które z czasem stają się cynamonowe do rdzawych. Trzon jest zwykle w tym samym kolorze co kapelusz. Występuje w lasach iglastych i liściastych.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Craterellus cornucopioides', 'Lejkowiec dęty', 'Craterellus cornucopioides, znany jako róg obfitości, to grzyb o charakterystycznym lejkowatym kształcie przypominającym róg. Kapelusz ma 4-8 cm głębokości i 4-10 cm szerokości, z falistym, pomarszczonym brzegiem. Miąższ jest cienki, kruchy i biały do szarawego. Występuje późnym latem i wczesną jesienią w lasach iglastych i mieszanych, często w towarzystwie świerków, sosen lub brzóz. Ceniony za unikalny smak i teksturę.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula ochroleuca', 'Gołąbek ochrowy', 'Russula ochroleuca, znana jako Gołąbek ochrowy lub Żółty gołąbek pospolity, występuje w różnych typach lasów od połowy lata. Kapelusz ma średnicę 3-8 cm, żółty, czasem z zielonkawym odcieniem. Blaszki są kremowobiałe, przyrośnięte, wąskie i kruche, z wiekiem ciemnieją. Trzon o wysokości 4-7 cm jest biały, z wiekiem szarzeje. Miąższ jest biały i kruchy. Jest grzybem mikoryzowym.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus luteus', 'Maślak zwyczajny', 'Suillus luteus, znany jako Maślak zwyczajny, to średniej do dużej wielkości borowik z lepkim, brązowym kapeluszem o charakterystycznym stożkowatym kształcie, który później spłaszcza się. Skórka jest łatwo ściągalna. Pod spodem znajdują się drobne, okrągłe pory, początkowo żółte, z czasem oliwkowo-ciemnożółte. Trzon ma membranowaty pierścień, początkowo białawy, ciemniejący z wiekiem. Miąższ jest białawy z żółtym odcieniem, miękki.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma sulphureum', 'Gąska siarkowa', 'Tricholoma sulphureum to grzyb o charakterystycznej siarkowożółtej barwie, często z czerwonobrązowymi lub oliwkowymi odcieniami. Kapelusz o średnicy 3-8 cm jest wypukły, zazwyczaj z falistym brzegiem. Blaszki są jaskrawożółte, szerokie i rzadkie. Trzon ma 3-5 cm długości, jest żółty z czerwonawymi włóknami. Wydziela nieprzyjemny zapach przypominający gaz węglowy, co związane jest z zawartością siarki. Występuje w lasach liściastych i iglastych.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Boletus edulis', 'Borowik szlachetny', 'Borowik szlachetny (Boletus edulis Bull.) – gatunek grzybów z rodziny borowikowatych. Jeden z najbardziej cenionych grzybów jadalnych. Kapelusz 5-25 cm średnicy, od jasnobrązowego po ciemnobrązowy, półkulisty, później poduszkowaty. Rurki białawe, potem żółtozielone, nie sinieją po uszkodzeniu. Trzon pękaty, jasnobrązowy z charakterystyczną jasną siateczką. Miąższ biały, nie zmienia barwy. Występuje w lasach iglastych i liściastych, tworzy mikoryzę z różnymi gatunkami drzew.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Boletus separans', 'Borowik oddzielający', 'Boletus separans to gatunek grzyba z rodziny borowikowatych (Boletaceae). Został opisany jako nowy dla nauki w 1873 roku przez amerykańskiego mykologa Charlesa Hortona Pecka. Badania molekularne z 2013 roku wykazały, że jest bardziej spokrewniony z rodzajem Boletus sensu stricto niż z Xanthoconium, do którego był wcześniej klasyfikowany. Występuje głównie w Ameryce Północnej. Jest cenionym grzybem jadalnym.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Gyroporus castaneus', 'Piaskowiec kasztanowaty', 'Gyroporus castaneus, znany jako piaskowiec kasztanowaty, to niewielki grzyb o białych porach i brązowym kapeluszu o średnicy 3-10 cm, który ciemnieje z wiekiem. Charakterystyczną cechą jest pusty trzon z komorami widocznymi po przekrojeniu. Występuje głównie w lasach dębowych na kwaśnych, piaszczystych glebach. Grzyb mikoryzowy, spotykany w Europie, Ameryce Północnej i Azji, choć rzadko w zachodniej części Ameryki Północnej. Jest jadalny, o przyjemnym orzechowym smaku.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Gyroporus cyanescens', 'Piaskowiec modrzak', 'Gyroporus cyanescens, znany jako piaskowiec modrzak, charakteryzuje się żółtawym do płowego kapeluszem o włóknistej, chropowatej powierzchni, osiągającym do 12 cm średnicy. Trzon ma podobną barwę co kapelusz lub jaśniejszą i jest wydrążony w komory. Wszystkie części grzyba intensywnie niebieszczą po uszkodzeniu lub przecięciu. Występuje w Eurazji, Australii i wschodniej Ameryce Północnej, gdzie rośnie na ziemi w lasach iglastych i mieszanych. Jest jadalny i uważany za grzyb wysokiej jakości.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactarius rufus', 'Mleczaj rudy', 'Mleczaj rudy (Lactarius rufus (Scop.) Fr.) – gatunek grzybów należący do rodziny gołąbkowatych (Russulaceae). Ma czerwonawobrązowy kapelusz ze spiczastym garbkiem. Po uszkodzeniu wydziela białe, wodniste mleczko o piekącym smaku. Występuje masowo w lasach iglastych, szczególnie pod świerkami i sosnami, na glebach kwaśnych. Mimo ostrego smaku, po odpowiednim przygotowaniu (moczenie, gotowanie) jest jadalny w Rosji i na Ukrainie. W stanie surowym może powodować dolegliwości pokarmowe.', 'warunkowo jadalny');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Lactifluus volemus', 'Mleczaj dębowy', 'Lactifluus volemus (dawniej Lactarius volemus) to grzyb jadalny znany również jako "płaczący mleczaj". Mimo nieprzyjemnego rybiego zapachu, który rozwija się po zebraniu, jest polecany do celów kulinarnych. Zapach znika podczas gotowania. Najlepiej przygotowywać go poprzez powolne gotowanie. Jest jednym z najpopularniejszych dzikich grzybów jadalnych zbieranych do spożycia i sprzedaży w Nepalu oraz w prowincji Junnan w Chinach. Zawiera wysoką zawartość białka i węglowodanów.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula emetica', 'Gołąbek wymiotny', 'Gołąbek wymiotny (Russula emetica (Schaeff.) Pers.) – gatunek grzybów z rodziny gołąbkowatych (Russulaceae). Ma jaskrawoczerwony, łatwo łuszczący się kapelusz o średnicy 3-10 cm i białe blaszki. Miąższ biały, kruchy, o bardzo ostrym, piekącym smaku. Nazwa gatunkowa odnosi się do wywoływania wymiotów po spożyciu. Występuje głównie w wilgotnych lasach iglastych, szczególnie pod sosnami. Jest trujący w stanie surowym, powoduje zaburzenia żołądkowo-jelitowe.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula virescens', 'Gołąbek zielonawy', 'Gołąbek zielonawy (Russula virescens (Schaeff.) Fr.) – gatunek grzybów z rodziny gołąbkowatych (Russulaceae). Jeden z najbardziej cenionych gołąbków. Charakteryzuje się zielonkawym kapeluszem o średnicy 5-15 cm, który pęka na małe poletka tworząc mozaikowy wzór. Blaszki i trzon są białe. Miąższ biały, kruchy, o łagodnym, orzechowym smaku. Występuje w lasach liściastych, głównie pod dębami i bukami. Jest doskonałym grzybem jadalnym, nadającym się do spożycia również na surowo.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus grevillei', 'Maślak żółty', 'Suillus grevillei (Singer) Kuntze, znany jako maślak żółty, to grzyb z charakterystycznym żółtym do pomarańczowobrązowego, lepkim kapeluszem. Rurki są żółte i brunatnieją po uszkodzeniu. Trzon ma tej samej barwy co kapelusz, często z wyraźnym pierścieniem. Rośnie wyłącznie pod modrzewiami, z którymi tworzy związki mikoryzowe. Jest jadalny po obraniu lepkiej skórki z kapelusza. Występuje powszechnie w Europie i Ameryce Północnej, szczególnie w plantacjach modrzewia.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma aurantium', 'Gąska pomarańczowa', 'Tricholoma aurantium (Schaeff.) Ricken, znana jako gąska pomarańczowa, to grzyb o pomarańczowobrązowym do czerwonawego kapeluszu o średnicy 5-10 cm. Blaszki są białawe do żółtawych, często z rdzawymi plamami. Trzon podobnej barwy co kapelusz, z łuseczkami. Miąższ białawy, o gorzkim smaku i nieprzyjemnym zapachu. Występuje w lasach iglastych, szczególnie pod świerkami. Jest niejadalny ze względu na gorzki smak i właściwości przeczyszczające.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma portentosum', 'Gąska niekształtna', 'Tricholoma portentosum (Fr.) Quél., znana jako gąska niekształtna, to grzyb o szarym kapeluszu z żółtawym odcieniem, szczególnie na brzegu. Kapelusz ma średnicę 5-15 cm, jest wypukły, później rozpostarty. Blaszki są białe do szarawych. Trzon biały, często z żółtawym zabarwieniem. Miąższ biały, o przyjemnym mącznym zapachu i smaku. Występuje późną jesienią w lasach iglastych, szczególnie pod sosnami. Jest dobrym grzybem jadalnym.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita muscaria', 'Muchomor czerwony', 'Amanita muscaria, znany jako muchomor czerwony, to jeden z najbardziej rozpoznawalnych grzybów na świecie. Charakteryzuje się czerwonym kapeluszem pokrytym białymi plamkami (resztkami osłony) oraz białymi blaszkami. Występuje w lasach strefy umiarkowanej i borealnej półkuli północnej, tworząc związki mikoryzowe z różnymi drzewami. Zawiera substancje psychoaktywne, w tym muscymol i kwas ibotenowy, które są przyczyną zatruć. Jego nazwa pochodzi od tradycyjnego zastosowania jako środek owadobójczy.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita pantherina', 'Muchomor plamisty', 'Amanita pantherina, znany jako muchomor plamisty, to grzyb psychoaktywny z rodzaju Amanita. Zawiera muscymol i kwas ibotenowy. Kapelusz brązowy z charakterystycznymi białymi plamkami. Występuje zarówno w lasach liściastych (szczególnie bukowych) jak i iglastych w Europie i zachodniej Azji, od późnego lata do jesieni. Jest grzybem ektomikoryzowym, żyjącym w symbiozie z korzeniami drzew. Stosunkowo rzadki, bywa mylony z jadalnym muchomorem czerwieniejącym.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Boletus pinophilus', 'Borowik sosnowy', 'Boletus pinophilus, znany również jako borowik sosnowy, to ceniony grzyb jadalny występujący w lasach iglastych półkuli północnej. Charakteryzuje się bogatym i złożonym smakiem, szczególnie ceniony w kuchni japońskiej. Grzyb ten zawiera wysokie poziomy białka, błonnika, przeciwutleniaczy oraz znaczne ilości witaminy C, potasu i fosforu. W tradycyjnej medycynie chińskiej używany do wspomagania trawienia, obniżania stanów zapalnych i wzmacniania układu odpornościowego.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Imleria badia', 'Podgrzybek brunatny', 'Imleria badia, znany jako podgrzybek brunatny, to grzyb charakteryzujący się kasztanowo-brązowym kapeluszem, początkowo kulistym, później spłaszczonym do średnicy 15 cm. Rurki na spodzie kapelusza są początkowo kremowe do bladożółtych, z wiekiem stają się zielonkawo-żółte i sinieją po uszkodzeniu. Miąższ jest białawy lub żółtawy, w niektórych miejscach sinieje po uszkodzeniu, szczególnie w wilgotną pogodę. Trzon ma delikatne podłużne prążki i siateczkowanie w górnej części.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Leccinum aurantiacum', 'Koźlarz czerwony', 'Leccinum aurantiacum, znany jako koźlarz czerwony, to dobry grzyb jadalny, który musi być dokładnie ugotowany, aby uniknąć problemów trawiennych. Kapelusz pomarańczowy, trzon pokryty charakterystycznymi ciemnymi łuseczkami. Występuje od lata do jesieni. Nazwa Leccinum pochodzi od włoskiego słowa Leccino, używanego do opisu borowików o chropowatych trzonach, a aurantiacum odnosi się do pomarańczowego koloru. Jest popularnym grzybem zbieranym w Europie.', 'tak, po dokładnym ugotowaniu');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Leccinum scabrum', 'Koźlarz babka', 'Leccinum scabrum, znany jako koźlarz babka, to średniej do dużej wielkości borowik z brązowym kapeluszem, szaro-białymi rurkami i białym do szarego trzonem pokrytym szaro-czarnymi łuskami. Rośnie wyłącznie pod brzozami, często na wilgotnym podłożu. Jest pospolitym jadalnym grzybem jesiennym, choć nie tak smacznym jak najlepsze borowiki. Miąższ młodych okazów jest białawy i zwykle nie zmienia znacząco koloru po uszkodzeniu.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Macrolepiota procera', 'Czubajka kania', 'Macrolepiota procera, znana jako czubajka kania, to wyjątkowo ceniony grzyb jadalny w Europie ze względu na swój duży rozmiar i wszechstronność kulinarna. Kapelusz początkowo kulisty i jasnobrązowy z ciemniejszymi łuskami, rozrasta się do 10-25 cm średnicy z małym centralnym garbkiem. Trzon jest niejadalny ze względu na włóknistą strukturę, chyba że zostanie wysuszony i zmielony na proszek. Posiada duży, podwójnie ząbkowany pierścień wokół trzonu, który często staje się ruchomy i opada do podstawy.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula paludosa', 'Gołąbek bagienny', 'Russula paludosa, znany jako gołąbek bagienny, to jadalny gołąbek z wypukłym do wklęsłego, pomarańczowo-czerwonym kapeluszem, z żółtawymi przebarwieniami w centrum i lekko lepką powierzchnią gdy jest wilgotna. Występuje powszechnie w Europie i Ameryce Północnej. Rośnie na ziemi w lasach mieszanych i iglastych od wczesnego lata do wczesnej jesieni, szczególnie w towarzystwie sosen. Trzon jest wyjątkowo długi jak na gołąbka, osiągając 4-15 cm.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillellus luridus', 'Borowik ponury', 'Suillellus luridus to masywny borowik z oliwkowo-brązowym kapeluszem do 20 cm średnicy, z małymi czerwonymi rurkami na spodzie. Masywny ochrowy trzon osiąga 8-14 cm wysokości i 1-3 cm szerokości, z charakterystycznym czerwonym siateczkowaniem. Sinieje po uszkodzeniu lub przecięciu. Choć jadalny po ugotowaniu, może powodować dolegliwości żołądkowe w stanie surowym. Bywa mylony z trującym borowikiem szatańskim, choć ten ostatni ma jaśniejszy kapelusz.', 'tak, po ugotowaniu');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma equestre', 'Gąska zielonka', 'Tricholoma equestre, znany również jako Tricholoma flavovirens lub gąska zielonka, to grzyb tworzący mikoryzę z sosnami. Był ceniony jako grzyb jadalny na całym świecie, szczególnie we Francji i centralnej Portugalii. Mimo że uważany za smaczny, odnotowano przypadki zatruć po jego spożyciu. Badania wykazały, że posiada właściwości toksyczne, choć twierdzenia te są kwestionowane. Ze względu na potencjalne ryzyko, nie jest obecnie zalecany do spożycia.', 'nie (potencjalnie trujący)');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma terreum', 'Gąska ziemista', 'Tricholoma terreum, znana jako gąska ziemista, to grzyb o szarym kapeluszu z rodzaju Tricholoma. Występuje w lasach iglastych Europy i Ameryki Północnej, a także pod introdukowanymi sosnami w Australii i Nowej Zelandii. Owocniki pojawiają się pod drzewami iglastymi, szczególnie sosnami i świerkami, od późnego lata do późnej jesieni. Ma łagodny smak i była powszechnie uważana za dobry grzyb jadalny. Badania wykazały, że tylko nienormalnie duża ilość tych grzybów może wywołać toksyczne efekty.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Xerocomellus chrysenteron', 'Podgrzybek złotopory', 'Xerocomellus chrysenteron, znany jako podgrzybek złotopory lub czerwony borowik pękający, charakteryzuje się kapeluszem początkowo ciemnobrązowym, prawie czarnym, o aksamitnej powierzchni, która z wiekiem pęka, ukazując czerwonawy miąższ. Występuje pojedynczo lub w małych grupach pod bukami i drzewami iglastymi od lata do jesieni. Rurki są cytrynowożółte, z wiekiem stają się brudnożółte, a u starszych okazów mogą powoli sinieć po uszkodzeniu. Jadalny po ugotowaniu, ale ma słaby smak i konsystencję.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Agaricus xanthodermus', 'Pieczarka żółtawi±ca', 'Pieczarka żółtawiąca charakteryzuje się białym kapeluszem o średnicy 5-20 cm, który po uszkodzeniu lub przecięciu intensywnie żółknie, szczególnie u podstawy trzonu. Ma nieprzyjemny zapach przypominający fenol, jodynę lub atrament drukarski. Często występuje na trawnikach i w ogrodach w obszarach miejskich. Jest jedną z głównych przyczyn zatruć grzybami dzikimi, powodując nudności, skurcze żołądka, wymioty i biegunkę.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Amanita phalloides', 'Muchomor zielonawy', 'Muchomor zielonawy, znany również jako muchomor sromotnikowy, to jeden z najbardziej śmiertelnie trujących grzybów, odpowiedzialny za ponad 90% zgonów związanych z zatruciem grzybami w Europie. Kapelusz o średnicy 5-15 cm ma kolor od oliwkowozielonego do czarnozielonego. Zawiera amanityny, które uszkadzają wątrobę i nerki. Charakterystyczne są opóźnione objawy zatrucia, które początkowo ustępują, dając złudne poczucie wyzdrowienia, po czym powracają ze zdwojoną siłą.', 'nie (śmiertelnie trujący)');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Caloboletus calopus', 'Borowik goryczak', 'Caloboletus calopus (dawniej Boletus calopus) to atrakcyjnie wyglądający borowik, który jednak nie jest uważany za jadalny ze względu na bardzo gorzki smak, który nie znika po gotowaniu. Zawiera związki chemiczne takie jak kalopina i O-acetylcyklokalopina A, odpowiedzialne za gorzki smak. Odmiana frustosus może powodować poważne zatrucia. W Rosji i na Ukrainie bywa jednak spożywany.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Clavulina coralloides', 'Goździeńczyk koralowaty', 'Clavulina coralloides to jadalny grzyb koralowaty o białej do kremowej barwie. Można go rozpoznać po charakterystycznych spłaszczonych zakończeniach rozgałęzień z kilkoma drobnymi wypustkami ("cristate"). Ma rozgałęzioną strukturę wyrastającą z krótkiej, łodygowatej podstawy. Występuje w lasach i na łąkach, wyrasta z ziemi pod drzewami iglastymi i liściastymi. Owocniki pojawiają się od lata do jesieni.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Galerina marginata', 'Hełmówka obrzeżona', 'Galerina marginata, znana jako hełmówka obrzeżona, zawiera niebezpieczne, potencjalnie zagrażające życiu toksyny, które uszkadzają lub niszczą wątrobę i nerki. Małe, brązowe owocniki są trudne do identyfikacji i mogą być mylone z grzybami zawierającymi psylocybinę. Kapelusz o średnicy 1,5-5 cm ma kolor ochry do pomarańczowobrązowego lub żółtawobrązowego, z jaśniejszym brzegiem. Blaszki są gęste, przyrośnięte, bladobrązowe do żółtawych lub żółtobrązowych.', 'nie (śmiertelnie trujący)');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Gyromitra esculenta', 'Piestrzenica kasztanowata', 'Gyromitra esculenta to grzyb workowy o charakterystycznym, nieregularnym kapeluszu przypominającym mózg, o ciemnobrązowej barwie, osiągającym 10 cm wysokości i 15 cm szerokości, osadzony na grubym, białym trzonie. Pomimo potencjalnej śmiertelności w stanie surowym, jest nadal często spożywany po obróbce termicznej w niektórych regionach Europy i Ameryki Północnej. Zawiera giromitry, które hydrolizują do toksycznego monometylohydrazyny, wpływającego na wątrobę i układ nerwowy.', 'nie (potencjalnie śmiertelny)');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Hypholoma fasciculare', 'Maślanka wiązkowa', 'Hypholoma fasciculare, znana jako maślanka wiązkowa, to grzyb trujący o bardzo gorzkawym smaku. Kapelusz o średnicy 2-7 cm ma kolor siarkowożółty, często z brązowawym centrum. Blaszki początkowo są żółte, później oliwkowozielone, a w końcu czarnieją wraz z dojrzewaniem zarodników. Rośnie w kępach na pniach, powalonych kłodach i innym martwym drewnie, głównie drzew liściastych. Zatrucie może powodować bóle żołądka, nudności, a rzadko także tymczasowy paraliż i zaburzenia widzenia.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Paxillus involutus', 'Krowiak podwinięty', 'Paxillus involutus, znany jako krowiak podwinięty, to grzyb niegdyś uważany za jadalny, obecnie zaliczany do trujących. Kapelusz o średnicy 5-15 cm, początkowo wypukły, z silnie podwiniętym brzegiem, później spłaszczony lub lekko wklęsły, o barwie od ochrowobrązowej do szarawej. Zawiera inwolutynę, która powoduje hemolizę (niszczenie czerwonych krwinek), co może prowadzić do anemii i niewydolności nerek, a w najcięższych przypadkach do śmierci.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Russula emetica', 'Gołąbek wymiotny', 'Russula emetica, znany jako gołąbek wymiotny, to grzyb o jaskrawoczerwonym, lepkim kapeluszu o średnicy 2,5-8,5 cm, z promieniście biegnącymi rowkami na brzegu. Blaszki są gęste, białe do kremowobiałych. Miąższ jest biały, kruchy, o bardzo ostrym i pieprznym smaku. Powoduje objawy głównie żołądkowo-jelitowe: nudności, biegunkę, wymioty i kolkowe bóle brzucha, które zazwyczaj ustępują samoistnie po usunięciu zjedzonego materiału z przewodu pokarmowego.', 'nie');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus granulatus', 'Maślak ziarnisty', 'Suillus granulatus, znany jako maślak ziarnisty, to borowik o brązowym do pomarańczowego kapeluszu o średnicy do 10 cm, który jest tłusty lub lepki w dotyku. Młode okazy wydzielają mleczny płyn z porów, stąd angielska nazwa "Weeping Bolete". Tworzy związki mikoryzowe z drzewami iglastymi, najczęściej z sosną zwyczajną. Jest jadalny, ale kapelusz jest dość tłusty, a u niektórych osób może powodować dolegliwości żołądkowe.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Suillus variegatus', 'Maślak pstry', 'Suillus variegatus, znany jako maślak pstry, wyróżnia się na tle innych gatunków z rodzaju Suillus nietypowo suchą i filcowatą powierzchnią kapelusza, pokrytą drobnymi łuskami. Kapelusz o średnicy 4-10 cm ma kolor żółtawoochrowy do żółtobrązowego. Miąższ jest bladożółty i miękki, wyraźnie sinieją nad warstwą rurek po przecięciu. Tworzy związki mikoryzowe z drzewami iglastymi, szczególnie z sosną zwyczajną.', 'tak');

INSERT INTO mushroom_info (name, name_pl, description, is_edible) VALUES
('Tricholoma equestre', 'Gąska zielonka', 'Tricholoma equestre, znana również jako Tricholoma flavovirens lub gąska zielonka, to grzyb tworzący związki mikoryzowe z sosnami. Status jadalności jest kontrowersyjny - w niektórych krajach jest uważany za trujący, w innych nadal zbierany i spożywany. Odnotowano przypadki rabdomiolizy po jego spożyciu, jednak najnowsze badania sugerują, że nie stanowi większego zagrożenia dla zdrowia niż inne gatunki grzybów obecnie uważane za jadalne. Zaleca się jednak ostrożność przy raportowaniu przypadków zatruć.', 'kontrowersyjny');
