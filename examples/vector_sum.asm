;PROGRAM DO WYZNACZANIA SUMY DODATNICH LICZB ZE 100-ELEMENTOWEGO WEKTORA
JEDEN:      DC      INTEGER(1)
STO:        DC      INTEGER(100)
WEKTOR:     DS      100*INTEGER
SUMA:       DS      INTEGER

MAIN:
        CALL    INIT_TAB        ;ZAINICJALIZUJ TABLICĘ
        SUB     0, 0            ;REJESTR 0 - WARTOŚĆ ZERO
        LD      1, 0            ;REJESTR 1 - TU OBLICZAMY SUMĘ
        LD      2, 0            ;REJESTR 2 - ITERATOR
        LDA     3, WEKTOR       ;REJESTR 3 - WSKAŹNIK NA OBECNIE ANALIZOWANY ELEMENT WEKTORA

LOOP:   CMP     0, 3(0)         ;ZERO ? I-TY ELEMENT WEKTORA
        JGE     NEGATIVE        ;JEŚLI ZERO JEST WIĘKSZE LUB RÓWNE I-TEMU ELEMENTOWI WEKTORA TO NIE DODAWAJ DO SUMY
        ADD     1, 3(0)         ;W.P.P. DODAWAJ
NEGATIVE:
        LDA     3, 3(4)         ;PRZESUŃ WSKAŹNIK WEKTORA NA NASTĘPNY ELEMENT
        ADD     2, JEDEN        ;ZWIĘKSZ ITERATOR O JEDEN
        CMP     2, STO          ;ITERATOR ? STO
        JZ      KONIEC_PROGRAMU ;JEŚLI ITERATOR RÓWNY STO TO SKOŃCZYL SIĘ WEKTOR
        JMP     LOOP            ;W.P.P. PRZEJDŹ DO NASTĘPNEGO ELEMENTU WEKTORA
KONIEC_PROGRAMU:
        ST      1, SUMA         ;ZAPISZ WYNIK W PAMIĘCI
        OUT     SUMA            ;WYPISZ WYNIK NA EKRAN
        EXIT                    ;KONIEC PROGRAMU



;INICJALIZUJE WEKTOR WARTOŚCIAMI Z PRZEDZIAŁU <-100, 99>
INIT_TAB:
        LD      0, STO
        LD      2, JEDEN
        ADD     2, 2
        MUL     0, 2
        LD      1, 0
        DIV     1, 2
        LDA     2, WEKTOR
        XOR     3, 3
NEXT:   RAND    4
        DIV     4, 0
        LD      4, 8
        SUB     4, STO
        ST      4, 2(0)
        INC     3
        LDA     2, 2(4)
        CMP     3, STO
        JL      NEXT
        RET

