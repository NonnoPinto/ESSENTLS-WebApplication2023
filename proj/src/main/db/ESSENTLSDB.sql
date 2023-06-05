PGDMP         #                {           essentls    15.2    15.2 <    T           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            U           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            V           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            W           1262    49458    essentls    DATABASE     �   CREATE DATABASE essentls WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United Kingdom.1252';
    DROP DATABASE essentls;
                essentls    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                essentls    false            X           0    0    SCHEMA public    ACL     +   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
                   essentls    false    5            P           1247    49460    diet    TYPE     �   CREATE TYPE public.diet AS ENUM (
    'No specific',
    'Vegetarian',
    'Vegan',
    'Halal',
    'Kosher',
    'Pescatarian'
);
    DROP TYPE public.diet;
       public          essentls    false    5            S           1247    49474    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          essentls    false    5            V           1247    49482    identity    TYPE     X   CREATE TYPE public.identity AS ENUM (
    'ID',
    'Passport',
    'Driver license'
);
    DROP TYPE public.identity;
       public          essentls    false    5            Y           1247    49490 	   paymethod    TYPE     M   CREATE TYPE public.paymethod AS ENUM (
    'Cash',
    'Card',
    'Bank'
);
    DROP TYPE public.paymethod;
       public          essentls    false    5            \           1247    49498 	   roletypes    TYPE     q   CREATE TYPE public.roletypes AS ENUM (
    'Organizer',
    'Participant',
    'Volunteer',
    'WaitingList'
);
    DROP TYPE public.roletypes;
       public          essentls    false    5            �            1259    49507    Causes    TABLE     [   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public."Causes";
       public         heap    essentls    false    5            �            1259    49510    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          essentls    false    5    214            Y           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          essentls    false    215            �            1259    49511    EventCauses    TABLE     f   CREATE TABLE public."EventCauses" (
    "eventId" integer NOT NULL,
    "causeId" integer NOT NULL
);
 !   DROP TABLE public."EventCauses";
       public         heap    essentls    false    5            �            1259    49514 	   EventTags    TABLE     m   CREATE TABLE public."EventTags" (
    "eventId" integer NOT NULL,
    tag character varying(255) NOT NULL
);
    DROP TABLE public."EventTags";
       public         heap    essentls    false    5            �            1259    49517    Events    TABLE     �  CREATE TABLE public."Events" (
    id integer NOT NULL,
    name text NOT NULL,
    description text,
    price numeric(10,2),
    visibility integer DEFAULT 3,
    location json,
    "maxParticipantsInternational" integer,
    "maxParticipantsVolunteer" integer,
    "eventStart" timestamp without time zone,
    "eventEnd" timestamp without time zone,
    "subscriptionStart" timestamp without time zone,
    "subscriptionEnd" timestamp without time zone,
    "withdrawalEnd" timestamp without time zone,
    "maxWaitingList" integer,
    attributes text[],
    thumbnail text,
    poster text,
    CONSTRAINT "Events_price_check" CHECK ((price >= (0)::numeric))
);
    DROP TABLE public."Events";
       public         heap    essentls    false    5            �            1259    49524    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          essentls    false    218    5            Z           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          essentls    false    219            �            1259    49525    Participants    TABLE     �   CREATE TABLE public."Participants" (
    "userId" integer NOT NULL,
    "eventId" integer NOT NULL,
    role public.roletypes,
    date timestamp without time zone,
    "attributeValues" json
);
 "   DROP TABLE public."Participants";
       public         heap    essentls    false    860    5            �            1259    49530    Payments    TABLE     8  CREATE TABLE public."Payments" (
    id integer NOT NULL,
    "userId" integer NOT NULL,
    "eventId" integer,
    method public.paymethod,
    amount numeric(10,2) NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    notes text,
    CONSTRAINT "Payments_amount_check" CHECK ((amount >= (0)::numeric))
);
    DROP TABLE public."Payments";
       public         heap    essentls    false    857    5            �            1259    49537    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          essentls    false    221    5            [           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          essentls    false    222            �            1259    49538    Tags    TABLE     I   CREATE TABLE public."Tags" (
    name character varying(255) NOT NULL
);
    DROP TABLE public."Tags";
       public         heap    essentls    false    5            �            1259    49541    Users    TABLE     �  CREATE TABLE public."Users" (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    "cardID" character varying(50),
    tier integer DEFAULT 0,
    "registrationDate" date,
    name character varying(50),
    surname character varying(50),
    sex public.gen,
    "dateOfBirth" date,
    nationality character varying(100),
    "homeCountryAddress" json,
    "homeCountryUniversity" character varying(150),
    "periodOfStay" integer,
    "phoneNumber" character varying(50),
    "paduaAddress" json,
    "documentType" public.identity,
    "documentNumber" character varying(50),
    "documentFile" text,
    "dietType" public.diet,
    allergies text[],
    "emailHash" character varying(255),
    "emailConfirmed" boolean DEFAULT false,
    "documentBytes" bytea,
    CONSTRAINT "Users_email_check" CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);
    DROP TABLE public."Users";
       public         heap    essentls    false    5    851    854    848            �            1259    49549    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          essentls    false    5    224            \           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          essentls    false    225            �           2604    49550 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          essentls    false    215    214            �           2604    49551 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          essentls    false    219    218            �           2604    49552    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          essentls    false    222    221            �           2604    49553    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          essentls    false    225    224            F          0    49507    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          essentls    false    214   �H       H          0    49511    EventCauses 
   TABLE DATA           =   COPY public."EventCauses" ("eventId", "causeId") FROM stdin;
    public          essentls    false    216   OI       I          0    49514 	   EventTags 
   TABLE DATA           5   COPY public."EventTags" ("eventId", tag) FROM stdin;
    public          essentls    false    217   �I       J          0    49517    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, "maxParticipantsInternational", "maxParticipantsVolunteer", "eventStart", "eventEnd", "subscriptionStart", "subscriptionEnd", "withdrawalEnd", "maxWaitingList", attributes, thumbnail, poster) FROM stdin;
    public          essentls    false    218   �I       L          0    49525    Participants 
   TABLE DATA           \   COPY public."Participants" ("userId", "eventId", role, date, "attributeValues") FROM stdin;
    public          essentls    false    220   sP       M          0    49530    Payments 
   TABLE DATA           Z   COPY public."Payments" (id, "userId", "eventId", method, amount, date, notes) FROM stdin;
    public          essentls    false    221   �Q       O          0    49538    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          essentls    false    223   �R       P          0    49541    Users 
   TABLE DATA           a  COPY public."Users" (id, email, password, "cardID", tier, "registrationDate", name, surname, sex, "dateOfBirth", nationality, "homeCountryAddress", "homeCountryUniversity", "periodOfStay", "phoneNumber", "paduaAddress", "documentType", "documentNumber", "documentFile", "dietType", allergies, "emailHash", "emailConfirmed", "documentBytes") FROM stdin;
    public          essentls    false    224   �R       ]           0    0    Causes_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Causes_id_seq"', 25, true);
          public          essentls    false    215            ^           0    0    Events_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Events_id_seq"', 10, true);
          public          essentls    false    219            _           0    0    Payments_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Payments_id_seq"', 12, true);
          public          essentls    false    222            `           0    0    Users_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Users_id_seq"', 15, true);
          public          essentls    false    225            �           2606    49555    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            essentls    false    214            �           2606    49557    EventCauses EventCauses_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT "EventCauses_pkey" PRIMARY KEY ("eventId", "causeId");
 J   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT "EventCauses_pkey";
       public            essentls    false    216    216            �           2606    49559    EventTags EventTags_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT "EventTags_pkey" PRIMARY KEY ("eventId", tag);
 F   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT "EventTags_pkey";
       public            essentls    false    217    217            �           2606    49561    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            essentls    false    218            �           2606    49563    Participants Participants_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT "Participants_pkey" PRIMARY KEY ("userId", "eventId");
 L   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT "Participants_pkey";
       public            essentls    false    220    220            �           2606    49565    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            essentls    false    221            �           2606    49567    Tags Tags_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Tags"
    ADD CONSTRAINT "Tags_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Tags" DROP CONSTRAINT "Tags_pkey";
       public            essentls    false    223            �           2606    49569    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            essentls    false    224            �           2606    49571    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            essentls    false    224            �           2606    49572    EventCauses fk_cause    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_cause FOREIGN KEY ("causeId") REFERENCES public."Causes"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_cause;
       public          essentls    false    214    216    3231            �           2606    49577    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          essentls    false    221    3237    218            �           2606    49582    EventTags fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 >   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_event;
       public          essentls    false    3237    218    217            �           2606    49587    EventCauses fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_event;
       public          essentls    false    3237    216    218            �           2606    49592    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          essentls    false    3237    218    220            �           2606    49597    EventTags fk_tag    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_tag FOREIGN KEY (tag) REFERENCES public."Tags"(name) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_tag;
       public          essentls    false    223    217    3243            �           2606    49602    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          essentls    false    3247    224    221            �           2606    49607    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON UPDATE CASCADE ON DELETE CASCADE;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          essentls    false    220    3247    224            F   �   x�5�M
�0@���)r�i�'p-��Mh�d N ?Bno\�||�+l�)��:N��P.\;j�k;���C-8�q�6��9	�pϮ�[A=�M����)�S�&�2�l�#��.`H���\��
��Xr�낈_�!/�      H   *   x��9 0��L&�Ʉ?���L�+H�f4�:���x�[��      I   _   x�3��O�L�	�/��2�ILw�,K�2�J�,N���K��2�q�X!E��\&�����9\��J�9R�S��s~nni^fI%W� ��      J   k  x���[o�8���_A�9�J��8ykn�z1��X(h���R����E����dǊ�6�t� �F�"�9gtM�	��f��k�K���q�{m�X����О+����Q�����h}�}�U�7%�w�z�.g�"2J�23��v���!����)9]:o��,%g��%�8$q����$N�ǒ�b_��XzKO��$w��A�`,�v�a�o=�����݀_����*?���Q��^:��7����V8/�\E7_*a�Й`�-8^�hSʌ�B�E���%�#��l���ٔ��B͸�|�̜r���¹t�UYQq+r6[3e2$�Mbw�\aVw8�0��Y��ZI��X��Ai�]��F��K��
a��[�K�(�Z��J ����:Ȋi�A�s�k����/�ulUH,��Ue��v�"��Ι)�Z�~�?b��N���}��T%�JP�>�*}&Uit���[�'Wkv�]��g7_
9M�)M�KW;�q��¼}��!3�e��.��K�+?�k�2++Jr�����R�p ���6`�M������Az-��-r!���,9���Ʌƒ,�{�:�kR�L����j��9�ז,�I�T��u�,ǏL8 [�1������\,e��i���������V�ɰZYV�Τg���aҳJq-|?: �]\O�kظ}^%��8ot+��A�:�B����I�P�R�<b��U�!$k����0npD<���C�*����� 	m��i��;�MHՠ@ �� -ĉ��	Z��E�F|�J���ѤdnX�!��2쟚+�Rl���cr+´Z���.I�!��I�p�U�RN���!�b���$
�͛:8fRqM˪��mY�Z�B��!@W�����?Q��{c��Xjr��急"�����#��Ml�L�Fѕ�kb�	�+%I7z�����v�5��fĲvA%�u/��=%�h��I)���9"��J�pє^��F� �s�͸�x>�/a�X�z)� �M�3w�.�}�Jlti�Ƽa���B���Ϙ�QEJ�=z��\��U�tH��?�� ��H���v-��2�q�`��N���C�El��ou���ҳ��3�;��;�P	(V�������_KC�EB�.�a�]?J�~��������7aSG,ax��Kv,���{:�֘�Gɫ5�`�gh~��ᷮh� ���MIh���3��i��2��^�U���O�k*���UMs,��jd.B����+eV$!mI�Qe�
C�R��N��D4�#kﬄR'3���nGU�c��m7��4d��ĭ����R4�P[�b�]�uN1�:�8��.`�._ӕ�_�E��;��]�@�K(J�i����'�%���+�C=���z�[�J6L�z�Fu9fWx��H�3]��N�ZR��c�&q34k���=q�������7�kx	��.�Q(l%E�	S��Q��PKN�J|��֏{R��E��k1Y�o�
(�S��s8#q�|�9Ѿ&!�%TlL�%[��8�vk��~��5�� a�^mL����(��Б&��"��b`g,<�K���tpK���Q$)����R��s�t��4Tgߣ������W�OW���o�O��6��:)���6�W-��������ѿ��nO      L   P  x���1o�0�g�+,�������*�R�v�B�R��Q�F��C�4�le�{�w��l`�Vݮh�g�1�H�LBL9HMRKɎ���3�{[ޙ��Bs�jJ����c�i����Eg]�nhT]Y�� �0�4�{�[�ę�1��S[�[�z�B���%c����#8��#@��l���k���:�G�8��@��1ЧϘbz��I)G�HZ�(���nsM%1B�)$�%E��3�ܞ����o�m|/��9҂N�LM����_E���`�9$>J#��4h�3�q�����U(�c$h��Q���3��pز.6�h��P�CX��{'��/Q����      M   �   x�u��
�0�s�{��$m7�*��������@ؠS��t����008��B��bJ��8O�8���,�C�d�� .gc
%�~|�Q
lG�ش�i3/Ij{iqR���?堓���4��W(.>L���9�Jn`)�{%���&�_��[r!B_!��;H���V!�k�`j      O   Q   x�O,�
N�����
IL�(JM1��K��tfY*WPbfq�o~^j%Wp~rfbNx~Q6W@jbr*�s~nni^fI%W� Ib�      P   y  x��XmS۸�,~����Ւ��PچfH�޹�/�$'�u�����N��}�� H��v��"��9�y�)������>�k_�0G�Ic��.-lƹ�I&9e&Üp�8�N��w�qDŔ|��p��t�=���!�e��G�����\�З��~q��C&e��%��r��F�MXVm}ϗ�.�VC�힯�\w�M[;������8~ET���|,	E2�6�j��DIB	����V%�Xf[>��׷V���,t;;i��]X�'�E�mNl0�[���4g|�le��dP����B�� �V���^N7��5�ȇ+�� �OW)6��Rj�[E�����d��ȌP������4刯���+4��te�%�Z�zK��vZ�Ͼ��ڂ'R%�S�~'�̇G؝��뽬QF��a��Y���u�2�M�u�.'���0_1w�+���Bi��˪q�}�3��&D�1E3��<c�p�2Q�<S�S�k��i�c���@ae 0��6��B3�{�n��.\=���q��|���؍��qk�2E�$"Z���ʰ؋�%� m[��qQh"���2�95��)!"�D�f2e��Xb��dh��Ah(�3 0V�y�*3��Mu=��7�a�ue�_Q��W�\]�9��eSˌњ)�S�D9�*�*N�5\Y]��K�����K�&C�^����o�MN��a�1���z56��T�]cma;�Y��N�8��4y��0!l���{�ސ���Ú7@�PW�H�t�]��B��sFK��¡���%�r�l���t�|E�>�n����`��8�sb�,-�S��/���9U`��\i��a�wެfA���tm��,=�@Go���<_��_M�'c��?X�95�V����]��z$y���2����#ż�T�}N1l�&����d,t����I	=j)���	���������[)�uh���/C8�WDeX1"�3�E���?�n9wBt���v>��������(Y��AZQ��Y��`;#:��`f�Ї��'��1p�����"�N�CV1t��6V��q����5:�8��=
��!��i[�ЕZ������rU��9����#w���_�*z�nâ@�3�u�%��r���D�� a�gp�F�LY���;���=	����B�zT�ݼ�K��=$t0�S��D�l�vo���7�����0|��3N��$����}�ﾲ���흅�	Ʌ�}�K��3|*�3\�`z��w�B*V2t��������:g�"�6�kt9Mz詽0�/]݆ڛ�ѷW�H�$�=4E�3��F��]�C׳"@GzQ������T�zLR��r�����>TSW�8~ T���.ܴr�no
��\Y��=� G��WK8�oQ�,wy�)�A;�eA�-ˤ�
[VP�H�p��
�c��z�Ы0��PE2���hu=�~���/�����k�!L̡EC��։.])���}7��\B�[o��D���jhw����6!bΟ_�;�)���N7��A�15p焋���F�1NR-��G�g+v�0hp����S M澝mqU�����*�w�s�����}��zٝ6���auԽ�6��{&��U�#]�P�ڸ{ߗ�|�'\G7�������;�$     