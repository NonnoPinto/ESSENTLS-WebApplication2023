PGDMP     0    )                {           ESSENTLS    10.23    15.2 0    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17194    ESSENTLS    DATABASE     �   CREATE DATABASE "ESSENTLS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Malaysia.1252';
    DROP DATABASE "ESSENTLS";
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    8                        3079    17217    chkpass 	   EXTENSION     ;   CREATE EXTENSION IF NOT EXISTS chkpass WITH SCHEMA public;
    DROP EXTENSION chkpass;
                   false    8            �           0    0    EXTENSION chkpass    COMMENT     J   COMMENT ON EXTENSION chkpass IS 'data type for auto-encrypted passwords';
                        false    3                        3079    17227    citext 	   EXTENSION     :   CREATE EXTENSION IF NOT EXISTS citext WITH SCHEMA public;
    DROP EXTENSION citext;
                   false    8            �           0    0    EXTENSION citext    COMMENT     S   COMMENT ON EXTENSION citext IS 'data type for case-insensitive character strings';
                        false    2            �           1247    17452    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          postgres    false    8            �            1259    17537    Causes    TABLE     T   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name text[] NOT NULL
);
    DROP TABLE public."Causes";
       public            postgres    false    8            �            1259    17535    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    8    205            �           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    204            �            1259    17501    Events    TABLE     �  CREATE TABLE public."Events" (
    id integer NOT NULL,
    name text NOT NULL,
    description text[] NOT NULL,
    price numeric(10,2),
    visibility boolean DEFAULT false,
    location json,
    maxparticipantsinternational integer,
    maxparticipantsvolunteer integer,
    eventstart timestamp without time zone,
    eventend timestamp without time zone,
    subscriptionstart timestamp without time zone,
    subscriptionend timestamp without time zone,
    withdrawalend timestamp without time zone,
    maxwaitinglist integer,
    attributes text[],
    thumbnail text,
    poster text,
    CONSTRAINT "Events_price_check" CHECK ((price >= (0)::numeric))
);
    DROP TABLE public."Events";
       public            postgres    false    8            �            1259    17499    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    201    8            �           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    200            �            1259    17650    Participants    TABLE     �   CREATE TABLE public."Participants" (
    userid integer,
    eventid integer,
    role text,
    date timestamp without time zone,
    attributevalues text[]
);
 "   DROP TABLE public."Participants";
       public            postgres    false    8            �            1259    17514    Payments    TABLE     *  CREATE TABLE public."Payments" (
    id integer NOT NULL,
    userid integer,
    eventid integer,
    method text NOT NULL,
    amount numeric(10,2) NOT NULL,
    date date DEFAULT CURRENT_DATE NOT NULL,
    notes text[],
    CONSTRAINT "Payments_amount_check" CHECK ((amount >= (0)::numeric))
);
    DROP TABLE public."Payments";
       public            postgres    false    8            �            1259    17512    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    203    8            �           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    202            �            1259    17546    Tags    TABLE     .   CREATE TABLE public."Tags" (
    name text
);
    DROP TABLE public."Tags";
       public            postgres    false    8            �            1259    17478    Users    TABLE     �  CREATE TABLE public."Users" (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    password public.chkpass NOT NULL,
    cardid integer,
    tier integer,
    registrationdate date DEFAULT CURRENT_DATE NOT NULL,
    name character varying(50) NOT NULL,
    surname character varying(50) NOT NULL,
    sex public.gen,
    dateofbirth date,
    nationality character varying(50),
    homecountryaddress json,
    homecountryuniversity character varying(150),
    periodofstay character varying(50),
    phonenumber bigint,
    paduaaddress json,
    documenttype character varying(50),
    documentnumber character varying(50),
    documentfile character varying(50),
    diettype text[],
    allergies text[],
    emailhash text,
    emailconfirmed boolean DEFAULT false,
    CONSTRAINT "Users_email_check" CHECK (((email)::text ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'::text))
);
    DROP TABLE public."Users";
       public            postgres    false    645    3    3    8    8    3    8    8            �            1259    17476    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    8    199            �           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    198            �
           2604    17540 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    205    205            �
           2604    17504 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            �
           2604    17517    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    203    202    203            �
           2604    17481    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    199    198    199            �          0    17537    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    205   s8       �          0    17501    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, maxparticipantsinternational, maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, subscriptionend, withdrawalend, maxwaitinglist, attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    201   �8       �          0    17650    Participants 
   TABLE DATA           V   COPY public."Participants" (userid, eventid, role, date, attributevalues) FROM stdin;
    public          postgres    false    207   �8       �          0    17514    Payments 
   TABLE DATA           V   COPY public."Payments" (id, userid, eventid, method, amount, date, notes) FROM stdin;
    public          postgres    false    203   �8       �          0    17546    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    206   �8       ~          0    17478    Users 
   TABLE DATA           4  COPY public."Users" (id, email, password, cardid, tier, registrationdate, name, surname, sex, dateofbirth, nationality, homecountryaddress, homecountryuniversity, periodofstay, phonenumber, paduaaddress, documenttype, documentnumber, documentfile, diettype, allergies, emailhash, emailconfirmed) FROM stdin;
    public          postgres    false    199   9       �           0    0    Causes_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Causes_id_seq"', 1, false);
          public          postgres    false    204            �           0    0    Events_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."Events_id_seq"', 1, false);
          public          postgres    false    200            �           0    0    Payments_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."Payments_id_seq"', 1, false);
          public          postgres    false    202            �           0    0    Users_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Users_id_seq"', 1, false);
          public          postgres    false    198            �
           2606    17545    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    205            �
           2606    17511    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    201            �
           2606    17524    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    203            �
           2606    17491    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    199            �
           2606    17489    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    199                        2606    17530    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY (eventid) REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    203    2811    201                       2606    17661    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY (eventid) REFERENCES public."Events"(id) ON DELETE SET NULL;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          postgres    false    2811    201    207                       2606    17525    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    203    199    2809                       2606    17656    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY (userid) REFERENCES public."Users"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          postgres    false    2809    199    207            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      ~      x������ � �     