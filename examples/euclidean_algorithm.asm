;https://en.wikipedia.org/wiki/Euclidean_algorithm#Implementations
A:          DC      INTEGER(217260)
B:          DC      INTEGER(199665)
GCD:        DS      INTEGER

MAIN:
            LD      0, A
            LD      1, B
            CALL    MODULO_VERSION
            ST      0, GCD
            OUT     GCD
            EXIT


;OBLICZA NWD ALGORYTMEM EUKLIDESA BAZUJĄCYM NA DZIELENIU
;ARGUMENTY:
;REJESTR 0 - A
;REJESTR 1 - B
;OPERUJE NA:
;REJESTR 0, 1, 2, 3
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - NWD(A, B)
MODULO_VERSION:
            XOR     3, 3
LOOP1:
            CMP     1, 3
            JE      RETURN1
            LD      2, 0
            DIV     2, 1
            LD      0, 1
            LD      1, 8
            JMP     LOOP1
RETURN1:    RET


;OBLICZA NWD ALGORYTMEM EUKLIDESA BAZUJĄCYM NA ODEJMOWANIU
;ARGUMENTY:
;REJESTR 0 - A
;REJESTR 1 - B
;OPERUJE NA:
;REJESTR 0, 1
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - NWD(A, B)
SUBTRACTION_VERSION:
            CMP     0, 1
            JE      RETURN2
            JNG     ELSE
            SUB     0, 1
            JMP     SUBTRACTION_VERSION
ELSE:
            SUB     1, 0
            JMP     SUBTRACTION_VERSION
RETURN2:    RET


;OBLICZA NWD ALGORYTMEM EUKLIDESA BAZUJĄCYM NA REKURENCJI Z DZIELENIEM
;ARGUMENTY:
;REJESTR 0 - A
;REJESTR 1 - B
;OPERUJE NA:
;REJESTR 0, 1, 2
;WYMAGA DEKLARACJI:
;---
;ZWRACA:
;REJESTR 0 - NWD(A, B)
RECURSIVE_VERSION:
            ENTER
            XOR     2, 2
            PUSH    0
            PUSH    1
            CALL    RECURSION
            LEAVE
            RET

RECURSION:
            ENTER
            LD      1, 11(8)
            LD      0, 11(12)
            CMP     2, 1
            JE      RETURN3
            PUSH    1
            DIV     0, 1
            PUSH    8
            CALL    RECURSION
RETURN3:    LEAVE
            RET