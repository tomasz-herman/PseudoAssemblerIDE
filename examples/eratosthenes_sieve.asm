MAX:            DC      INTEGER(100)
TAB:            DC      100*INTEGER(1)
CZTERY:         DC      INTEGER(4)
SEPARATOR:      DC      CHAR('\n')

MAIN:
                XOR     5, 5        ;REJESTR 5 = stała 0
                XOR     0, 0        ;REJESTR 0 = i
                XOR     1, 1        ;REJESTR 1 = j
                LDA     2, TAB      ;REJESTR 2 = ADRES TABLICY
                DEC     2(0)        ;TAB[0] = false
                DEC     2(4)        ;TAB[1] = false
                INC     0
                INC     0           ;i = 2
L1:             LD      3, 0
                MUL     3, 3        ;REJESTR 3 = i^2
                CMP     3, MAX
                JGE     STOP1       ;Jeżeli i^2 >= max to koniec pętli
                LD      4, 0
                MUL     4, CZTERY
                ADD     4, 2
                LD      4, 4(0)
                CMP     4, 5
                JE      STOP2       ;Jeżeli tab[i] == false to nie wchodź w wewnętrzną pętlę
                LD      1, 0        ;j = i
L2:             ADD     1, 0        ;j += i
                CMP     1, MAX
                JGE     STOP2       ;Jeżeli j >= max to koniec wewnętrznej pętli
                LD      4, 1
                MUL     4, CZTERY
                ADD     4, 2
                ST      5, 4(0)     ;tab[j] = false
                JMP     L2          ;goto L2
STOP2:
                INC     0           ;i++
                JMP     L1          ;goto L1
STOP1:
                LD      4, 5        ;REJESTR 4 = iter
L3:             CMP     5, 2(0)
                JE      SKIP        ;Jeżeli tab[iter] == false to iter nie jest pierwsza
                OUT     4           ;W przeciwnym wypadku jest, więc wypisz
                COUT    SEPARATOR
SKIP:           LDA     2, 2(4)     ;Zwiększ adres tablicy o cztery
                INC     4           ;iter++
                CMP     4, MAX
                JL      L3          ;Jeżeli iter < max to kontynuuj pętlę
                EXIT