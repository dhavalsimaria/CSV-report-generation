SELECT   vprl.vart_nasa_nummer      nasa_nummer
,        'Basis verkoopprijs'       soort
,        TO_NUMBER( NULL )          formule
,        vprl.begindatum            begindatum 
,        vprl.einddatum             einddatum
,        vprl.basis_verkoopprijs    verkoopprijs
,        vprl.ean_kode_verkoopprijs ean_kode_verkoopprijs
,        ''                         ind_concurrentie_ongevoelig
,        TO_NUMBER( NULL )          percentage_prijsafstand        
,        TO_NUMBER( NULL )          bedrag_prijsafstand             
FROM     p1i005.l0basis_verkoopprijzen vprl
WHERE    NVL( vprl.einddatum, SYSDATE ) >= TRUNC( SYSDATE )
UNION ALL 
SELECT   fvkp.vart_nasa_nummer             nasa_nummer
,        'Formule verkoopprijs'            soort
,        fvkp.vkfr_nummer                  formule
,        fvkp.begindatum                   begindatum
,        fvkp.einddatum                    einddatum
,        fvkp.vaste_verkoopprijs           verkoopprijs
,        TO_NUMBER( NULL )                 ean_kode_verkoopprijs
,        fvkp.ind_concurrentie_ongevoelig  ind_concurrentie_ongevoelig
,        fvkp.percentage_prijsafstand      percentage_prijsafstand
,        fvkp.bedrag_prijsafstand          bedrag_prijsafstand
FROM     p1i005.l0formule_verkoopprijzen fvkp
WHERE    NVL( fvkp.einddatum, SYSDATE )     >= TRUNC( SYSDATE )
AND      fvkp.vkfr_nummer               NOT IN ( 5, 14 )
ORDER BY 1 
,        4 
,        2 
,        3 



