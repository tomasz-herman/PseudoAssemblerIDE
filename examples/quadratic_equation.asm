;PROGRAM DO WYZNACZANIA LICZBY ROZWIĄZAŃ RÓWNANIA KWADRATOWEGO 2x^2-8x+8=0
A:          DC      INTEGER(2)
B:          DC      INTEGER(-8)
C:          DC      INTEGER(8)
WYNIK:      DS      INTEGER
ZERO:       DC      INTEGER(0)
JEDEN:      DC      INTEGER(1)
DWA:        DC      INTEGER(2)
CZTERY:     DC      INTEGER(4)

MAIN:
        LD      0, B                ;REJESTR 0 -> B
        MUL     0, 0                ;REJESTR 0 -> B * B
        LD      1, CZTERY           ;REJESTR 1 -> CZTERY
        MUL     1, A                ;REJESTR 1 -> CZTERY * A
        MUL     1, C                ;REJESTR 1 -> CZTERY * A * C
        SUB     0, 1                ;REJESTR 0 -> B * B - CZTERY * A * C
        JG      DWA_ROZWIAZANIA     ;B * B - CZTERY * A * C >? 0
        JZ      JEDNO_ROZWIAZANIE   ;B * B - CZTERY * A * C =? 0
        JL      ZERO_ROZWIAZAN      ;B * B - CZTERY * A * C <? 0

DWA_ROZWIAZANIA:                    ;B * B - CZTERY * A * C > 0
        LD      2, DWA              ;REJESTR 2 -> DWA
        ST      2, WYNIK            ;ZAPISZ W PAMIĘCI WYNIK
        JMP     KONIEC_PROGRAMU
JEDNO_ROZWIAZANIE:                  ;B * B - CZTERY * A * C = 0
        LD      2, JEDEN            ;REJESTR 2 -> JEDEN
        ST      2, WYNIK            ;ZAPISZ W PAMIĘCI WYNIK
        JMP     KONIEC_PROGRAMU
ZERO_ROZWIAZAN:                     ;B * B - CZTERY * A * C < 0
        LD      2, ZERO             ;REJESTR 2 -> ZERO
        ST      2, WYNIK            ;ZAPISZ W PAMIĘCI WYNIK
        JMP     KONIEC_PROGRAMU

KONIEC_PROGRAMU:
        OUT     WYNIK               ;WYPISZ WYNIK NA EKRAN
        EXIT                        ;KONIEC