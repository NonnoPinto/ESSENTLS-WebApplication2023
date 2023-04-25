PGDMP     #                    {        
   ESSENTLSDB    15.2    15.2 :    T           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            U           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            V           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            W           1262    18040 
   ESSENTLSDB    DATABASE     �   CREATE DATABASE "ESSENTLSDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Malaysia.1252';
    DROP DATABASE "ESSENTLSDB";
                postgres    false            P           1247    18042    diet    TYPE     �   CREATE TYPE public.diet AS ENUM (
    'No specific',
    'Vegetarian',
    'Vegan',
    'Halal',
    'Kosher',
    'Pescatarian'
);
    DROP TYPE public.diet;
       public          postgres    false            S           1247    18056    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          postgres    false            V           1247    18064    identity    TYPE     X   CREATE TYPE public.identity AS ENUM (
    'ID',
    'Passport',
    'Driver license'
);
    DROP TYPE public.identity;
       public          postgres    false            Y           1247    18072 	   paymethod    TYPE     M   CREATE TYPE public.paymethod AS ENUM (
    'Cash',
    'Card',
    'Bank'
);
    DROP TYPE public.paymethod;
       public          postgres    false            \           1247    18080 	   roletypes    TYPE     q   CREATE TYPE public.roletypes AS ENUM (
    'Organizer',
    'Participant',
    'Volunteer',
    'WaitingList'
);
    DROP TYPE public.roletypes;
       public          postgres    false            �            1259    18089    Causes    TABLE     [   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public."Causes";
       public         heap    postgres    false            �            1259    18092    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    214            X           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    215            �            1259    18093    EventCauses    TABLE     f   CREATE TABLE public."EventCauses" (
    "eventId" integer NOT NULL,
    "causeId" integer NOT NULL
);
 !   DROP TABLE public."EventCauses";
       public         heap    postgres    false            �            1259    18096 	   EventTags    TABLE     m   CREATE TABLE public."EventTags" (
    "eventId" integer NOT NULL,
    tag character varying(255) NOT NULL
);
    DROP TABLE public."EventTags";
       public         heap    postgres    false            �            1259    18099    Events    TABLE     �  CREATE TABLE public."Events" (
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
       public         heap    postgres    false            �            1259    18106    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    218            Y           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    219            �            1259    18107    Participants    TABLE     �   CREATE TABLE public."Participants" (
    "userId" integer NOT NULL,
    "eventId" integer NOT NULL,
    role public.roletypes,
    date timestamp without time zone,
    "attributeValues" json
);
 "   DROP TABLE public."Participants";
       public         heap    postgres    false    860            �            1259    18112    Payments    TABLE     8  CREATE TABLE public."Payments" (
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
       public         heap    postgres    false    857            �            1259    18119    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    221            Z           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    222            �            1259    18120    Tags    TABLE     I   CREATE TABLE public."Tags" (
    name character varying(255) NOT NULL
);
    DROP TABLE public."Tags";
       public         heap    postgres    false            �            1259    18123    Users    TABLE     �  CREATE TABLE public."Users" (
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
    CONSTRAINT "Users_email_check" CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);
    DROP TABLE public."Users";
       public         heap    postgres    false    851    854    848            �            1259    18131    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    224            [           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    225            �           2604    18132 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    18133 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    18134    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �           2604    18135    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            F          0    18089    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    214   �F       H          0    18093    EventCauses 
   TABLE DATA           =   COPY public."EventCauses" ("eventId", "causeId") FROM stdin;
    public          postgres    false    216   G       I          0    18096 	   EventTags 
   TABLE DATA           5   COPY public."EventTags" ("eventId", tag) FROM stdin;
    public          postgres    false    217   HG       J          0    18099    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, "maxParticipantsInternational", "maxParticipantsVolunteer", "eventStart", "eventEnd", "subscriptionStart", "subscriptionEnd", "withdrawalEnd", "maxWaitingList", attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    218   �G       L          0    18107    Participants 
   TABLE DATA           \   COPY public."Participants" ("userId", "eventId", role, date, "attributeValues") FROM stdin;
    public          postgres    false    220   �I       M          0    18112    Payments 
   TABLE DATA           Z   COPY public."Payments" (id, "userId", "eventId", method, amount, date, notes) FROM stdin;
    public          postgres    false    221   �J       O          0    18120    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    223   ^K       P          0    18123    Users 
   TABLE DATA           P  COPY public."Users" (id, email, password, "cardID", tier, "registrationDate", name, surname, sex, "dateOfBirth", nationality, "homeCountryAddress", "homeCountryUniversity", "periodOfStay", "phoneNumber", "paduaAddress", "documentType", "documentNumber", "documentFile", "dietType", allergies, "emailHash", "emailConfirmed") FROM stdin;
    public          postgres    false    224   �K       \           0    0    Causes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Causes_id_seq"', 7, true);
          public          postgres    false    215            ]           0    0    Events_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Events_id_seq"', 7, true);
          public          postgres    false    219            ^           0    0    Payments_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Payments_id_seq"', 7, true);
          public          postgres    false    222            _           0    0    Users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."Users_id_seq"', 9, true);
          public          postgres    false    225            �           2606    18137    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    214            �           2606    18139    EventCauses EventCauses_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT "EventCauses_pkey" PRIMARY KEY ("eventId", "causeId");
 J   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT "EventCauses_pkey";
       public            postgres    false    216    216            �           2606    18141    EventTags EventTags_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT "EventTags_pkey" PRIMARY KEY ("eventId", tag);
 F   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT "EventTags_pkey";
       public            postgres    false    217    217            �           2606    18143    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    218            �           2606    18145    Participants Participants_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT "Participants_pkey" PRIMARY KEY ("userId", "eventId");
 L   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT "Participants_pkey";
       public            postgres    false    220    220            �           2606    18147    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    221            �           2606    18149    Tags Tags_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Tags"
    ADD CONSTRAINT "Tags_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Tags" DROP CONSTRAINT "Tags_pkey";
       public            postgres    false    223            �           2606    18151    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    224            �           2606    18153    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    224            �           2606    18154    EventCauses fk_cause    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_cause FOREIGN KEY ("causeId") REFERENCES public."Causes"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_cause;
       public          postgres    false    214    216    3231            �           2606    18159    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    221    3237    218            �           2606    18164    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          postgres    false    3237    218    220            �           2606    18169    EventTags fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 >   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_event;
       public          postgres    false    3237    217    218            �           2606    18174    EventCauses fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventCauses"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."EventCauses" DROP CONSTRAINT fk_event;
       public          postgres    false    3237    218    216            �           2606    18179    EventTags fk_tag    FK CONSTRAINT     �   ALTER TABLE ONLY public."EventTags"
    ADD CONSTRAINT fk_tag FOREIGN KEY (tag) REFERENCES public."Tags"(name) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."EventTags" DROP CONSTRAINT fk_tag;
       public          postgres    false    223    217    3243            �           2606    18184    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    3247    224    221            �           2606    18189    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          postgres    false    220    3247    224            F   H   x�3�tN,-NU��K�2��C��a쌢�T.(�-������,K�2�J�r|��R+��9���b���� �Y�      H   '   x�3�4�2�4�2�4b#.NS.3N3.sNs�=... D�      I   Q   x�3�O,�2��O�L�	�/��2�ILw�,K
%f����Vr���K���@�����T.�����.sd�1z\\\ �N�      J   @  x���Mo�0��V���ve��u=U��ŀ�$F��Ӿ��P���Ӵ�I���	�/�C�ǝ*�
�mt��:'�7HGߓg�M&	���'E�?*�`~�d�����8��H;��5����Y�;-��$?� �R�a�K(\��	'f��.e]6
��'�w}��@:g�c�T�M������Ml�pȕ�Ԥ��O'���s�Ve�m����޷��u�֔NY�q�y�"^�Цo�;x�w!�U���;zNШ�]Jbᗌ¼K��}�1��B��	��ad��Qރ#����Ӑ�����^?+��bya}_Y��i`�_�L�R�B(@;�N�L��Ҹ���?�N�ԟLe#�:�r�}����
z�zh����T���QgT�FU���$��8Q�]\�u�l��d�k���驱��TMzk��F]w�堥��q�~��E�{���2��� -�O��/WU����J]*<�C��X<� 3�Y��m@��G_蜞�Z
�9 Yˀd-��R\}��Pk���+v�3��=���ݲ(�	��;�M�N3o�ʹe_�27�f>��C����M�ΐ      L   �   x��ұ
�0����%��KҴ��$t���R5�I!M��Ai�3Ï?w$T�m�yh�d���������ޙ���ָ��2���t�������:-���-ug�g���ED ���ƶ;3����R8���z�m,��h��l��c�F�E#�L�h���1��@���lb��Kp�b<�	!o}��      M   �   x�uϽ
�0���~�I�ӽ��[2t��!Pp��y���P(	�>�@�p�ʖy����:ts6�OL/s�r��Я}N[���[��8��6��}�����n_1Z��h��Z%����耒���?п���<�����;�      O   A   x�O,�
N�����
IL�(JM1��K��tfY*WPbfq�o~^j%Wp~rfbNx~Q6W� �/�      P   �  x�͖Ms�6�ϫ_��9�~HdNUb'U{4r�^|�IHB���t�߻ �A���v;�m���}�.׃����`�~[����X�`�&<�q�>p�����Mv�4�R|H=��L�s�yL%����Wݷ]�E^�MW�Ӹ�]܏�X2�k�����R��iAe©y��&+ك�ڧ1�嚱S��:���}����p���Δ&Ꞃ�O\��!��|w0����i�[+�?Q\Fe�8|�����#Li���΍���7�����~��kU�~��&Y����+��]�;T`�S�;c!�^��K<u��^2!�<[>�X�fU�f�+���/�����&ާ����D�&9���
VgY���B�P�B�A�C�/P���?�P�vV���V�����X��<nК*�SV�Xl��e]��E�	�V�n�2c�[_F�>KE�7e�#�_��~z�I1<E�H%�y��5�5a^��+��>�TSYAi�Ɔ�]U���@u�1aW��Q�4aْc1ۏ�4>��V�dt��&R��4�E�l����;-�b��	�t	xnD���#��+�uXc���Vn����E��\vʰ�W�8 AM+��f3c����'��4��9������JtUB%�����|E�DO>G��V��^���ňv��;*y�&�ET�06ZĻM�o8��ly^��2Tg��#V��k*;�L��,k�m�N�.�Q�њ[�ڭ�[��ZN������}-Z���mN-�T���6���4��k�a8:���S��UC����=�Km�4$�:���BU;�S���-�����Df,ΛBU���Į�� B;x��2�$�玃���pɩ��q�&y�JQH�	*�z?�c�J/\���"4�Y�	l��V���q�!�'��P�gMA�a�(|;���ϵ��Y�����     