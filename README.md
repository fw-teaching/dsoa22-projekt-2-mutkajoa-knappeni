# Projekt 2

### Boilerplate och färdiga klasser:

- En Main-klass med en tom main()-metod
- En uppsättning texter på olika språk i */assets/lang-samples/*
- En enum-klass (LangLabel) med språkkoder enligt ISO 639-1.

Alla övriga detaljer finns på itslearning.

### Våra/mina egna kommenterar om de olika skedena i projektet:

## Allmänt

Joakim: Jag tyckte detta projekt var jobbigare att arbeta på jämfört med projekt 1. Kanske på grund av att mera "frihet" i hur man löser problemet, det gör det svårare att åtminstone i början komma på var man ens skall börja på projektet. Jag är ocskå 99% säker på att det säkert finns ett mycket lättare sätt att lösa det här problemet, men vi hade kommit såpass långt på projektet när jag funderade längre på det att det kändes inte längre som ett alternativ att skriva om hela projektet. Så lösningen är nu denna, och så hoppas jag bara på godkänt i kursen.

## Del 1, 2 och 3

De tre första delarna i projektet hanteras i Utils klassens funktioner getCharFrequency, getThreeCharFrequency och getFirstcharFrequency. Funktionerna tar emot en string, och returnerar HashMaps med String och Float datatyper för frekvenserna av de olika bokstäverna eller bokstavskombinationerna.
Delarna 1 och 2 var relativt lätta att bygga upp, lite basic regex som tar bort alla oönskade karaktärer, siffror och mellanslag. Del 3 var sedan litet svårare, men gick nog också relativt lätt att lösa till sist också. Konvertera alla ord till en array av strings, och igen sanitera bort oönskat innehåll. Sedan ta första bokstaven i varje ord.

## Del 4

Del 4 var sedan mycket mer påfrestande, att hitta på hur man skall rangordna språken, och hur man lyckas med det i praktiken var definitivt en stor utmaning. Vi bestämde oss för att addera ihop det float värdet som varje bokstav eller bokstavskombination som finns i både "tränings datat" som var inbakat i projektfilerna. Destu mera likadana bokstäver och med större frekvenser, destu större totalt "score", och då blir det gissade språket det med högst total "score".
Åtminståne baserat på vårt testande så funkar vår algoritm, och lyckas ranka det rätta språket först.
En stor del av arbetet gick ut på att formatera datat och jämföra de två olika datasetten, eftersom datat är skrivet in i nested HashMaps så fick man fundera några gånger hur man skulle iterera igenom dem. Samma gällde sedan med att ordna datat i rätt ordning, eftersom HashMaps inte har ett index eller går att sortera på något enkelt vis.
