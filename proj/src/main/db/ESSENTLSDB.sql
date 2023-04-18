PGDMP                         {           postgres    15.2    15.2 3    C           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            D           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            E           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            F           1262    5    postgres    DATABASE     ~   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_Malaysia.1252';
    DROP DATABASE postgres;
                postgres    false            G           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3398                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            H           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            U           1247    17774    diet    TYPE     �   CREATE TYPE public.diet AS ENUM (
    'No specific',
    'Vegetarian',
    'Vegan',
    'Halal',
    'Kosher',
    'Pescatarian'
);
    DROP TYPE public.diet;
       public          postgres    false            O           1247    17581    gen    TYPE     K   CREATE TYPE public.gen AS ENUM (
    'female',
    'male',
    'others'
);
    DROP TYPE public.gen;
       public          postgres    false            R           1247    17766    identity    TYPE     X   CREATE TYPE public.identity AS ENUM (
    'ID',
    'Passport',
    'Driver license'
);
    DROP TYPE public.identity;
       public          postgres    false            ^           1247    17813 	   paymethod    TYPE     M   CREATE TYPE public.paymethod AS ENUM (
    'Cash',
    'Card',
    'Bank'
);
    DROP TYPE public.paymethod;
       public          postgres    false            j           1247    17853 	   roletypes    TYPE     q   CREATE TYPE public.roletypes AS ENUM (
    'Organizer',
    'Participant',
    'Volunteer',
    'WaitingList'
);
    DROP TYPE public.roletypes;
       public          postgres    false            �            1259    17841    Causes    TABLE     [   CREATE TABLE public."Causes" (
    id integer NOT NULL,
    name character varying(255)
);
    DROP TABLE public."Causes";
       public         heap    postgres    false            �            1259    17840    Causes_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Causes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Causes_id_seq";
       public          postgres    false    222            I           0    0    Causes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Causes_id_seq" OWNED BY public."Causes".id;
          public          postgres    false    221            �            1259    17802    Events    TABLE     �  CREATE TABLE public."Events" (
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
       public         heap    postgres    false            �            1259    17801    Events_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Events_id_seq";
       public          postgres    false    218            J           0    0    Events_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Events_id_seq" OWNED BY public."Events".id;
          public          postgres    false    217            �            1259    17861    Participants    TABLE     �   CREATE TABLE public."Participants" (
    "userId" integer NOT NULL,
    "eventId" integer NOT NULL,
    role public.roletypes,
    date timestamp without time zone,
    "attributeValues" json
);
 "   DROP TABLE public."Participants";
       public         heap    postgres    false    874            �            1259    17820    Payments    TABLE     8  CREATE TABLE public."Payments" (
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
       public         heap    postgres    false    862            �            1259    17819    Payments_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Payments_id_seq";
       public          postgres    false    220            K           0    0    Payments_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;
          public          postgres    false    219            �            1259    17847    Tags    TABLE     I   CREATE TABLE public."Tags" (
    name character varying(255) NOT NULL
);
    DROP TABLE public."Tags";
       public         heap    postgres    false            �            1259    17788    Users    TABLE     �  CREATE TABLE public."Users" (
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
       public         heap    postgres    false    853    847    850            �            1259    17787    Users_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."Users_id_seq";
       public          postgres    false    216            L           0    0    Users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."Users_id_seq" OWNED BY public."Users".id;
          public          postgres    false    215            �           2604    17844 	   Causes id    DEFAULT     j   ALTER TABLE ONLY public."Causes" ALTER COLUMN id SET DEFAULT nextval('public."Causes_id_seq"'::regclass);
 :   ALTER TABLE public."Causes" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    17805 	   Events id    DEFAULT     j   ALTER TABLE ONLY public."Events" ALTER COLUMN id SET DEFAULT nextval('public."Events_id_seq"'::regclass);
 :   ALTER TABLE public."Events" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    17823    Payments id    DEFAULT     n   ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);
 <   ALTER TABLE public."Payments" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    220    220            �           2604    17791    Users id    DEFAULT     h   ALTER TABLE ONLY public."Users" ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);
 9   ALTER TABLE public."Users" ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            >          0    17841    Causes 
   TABLE DATA           ,   COPY public."Causes" (id, name) FROM stdin;
    public          postgres    false    222    =       :          0    17802    Events 
   TABLE DATA             COPY public."Events" (id, name, description, price, visibility, location, "maxParticipantsInternational", "maxParticipantsVolunteer", "eventStart", "eventEnd", "subscriptionStart", "subscriptionEnd", "withdrawalEnd", "maxWaitingList", attributes, thumbnail, poster) FROM stdin;
    public          postgres    false    218   F=       @          0    17861    Participants 
   TABLE DATA           \   COPY public."Participants" ("userId", "eventId", role, date, "attributeValues") FROM stdin;
    public          postgres    false    224   �>       <          0    17820    Payments 
   TABLE DATA           Z   COPY public."Payments" (id, "userId", "eventId", method, amount, date, notes) FROM stdin;
    public          postgres    false    220   �?       ?          0    17847    Tags 
   TABLE DATA           &   COPY public."Tags" (name) FROM stdin;
    public          postgres    false    223   @       8          0    17788    Users 
   TABLE DATA           P  COPY public."Users" (id, email, password, "cardID", tier, "registrationDate", name, surname, sex, "dateOfBirth", nationality, "homeCountryAddress", "homeCountryUniversity", "periodOfStay", "phoneNumber", "paduaAddress", "documentType", "documentNumber", "documentFile", "dietType", allergies, "emailHash", "emailConfirmed") FROM stdin;
    public          postgres    false    216   J@       M           0    0    Causes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Causes_id_seq"', 5, true);
          public          postgres    false    221            N           0    0    Events_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Events_id_seq"', 5, true);
          public          postgres    false    217            O           0    0    Payments_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Payments_id_seq"', 5, true);
          public          postgres    false    219            P           0    0    Users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."Users_id_seq"', 5, true);
          public          postgres    false    215            �           2606    17846    Causes Causes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Causes"
    ADD CONSTRAINT "Causes_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Causes" DROP CONSTRAINT "Causes_pkey";
       public            postgres    false    222            �           2606    17811    Events Events_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public            postgres    false    218            �           2606    17867    Participants Participants_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT "Participants_pkey" PRIMARY KEY ("userId", "eventId");
 L   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT "Participants_pkey";
       public            postgres    false    224    224            �           2606    17829    Payments Payments_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);
 D   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT "Payments_pkey";
       public            postgres    false    220            �           2606    17851    Tags Tags_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Tags"
    ADD CONSTRAINT "Tags_pkey" PRIMARY KEY (name);
 <   ALTER TABLE ONLY public."Tags" DROP CONSTRAINT "Tags_pkey";
       public            postgres    false    223            �           2606    17800    Users Users_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);
 C   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_email_key";
       public            postgres    false    216            �           2606    17798    Users Users_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public            postgres    false    216            �           2606    17835    Payments fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 =   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_event;
       public          postgres    false    220    3228    218            �           2606    17873    Participants fk_event    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_event FOREIGN KEY ("eventId") REFERENCES public."Events"(id) ON DELETE SET NULL;
 A   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_event;
       public          postgres    false    218    224    3228            �           2606    17830    Payments fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 <   ALTER TABLE ONLY public."Payments" DROP CONSTRAINT fk_user;
       public          postgres    false    220    216    3226            �           2606    17868    Participants fk_user    FK CONSTRAINT     �   ALTER TABLE ONLY public."Participants"
    ADD CONSTRAINT fk_user FOREIGN KEY ("userId") REFERENCES public."Users"(id) ON DELETE SET NULL;
 @   ALTER TABLE ONLY public."Participants" DROP CONSTRAINT fk_user;
       public          postgres    false    3226    216    224            >   6   x�3�tN,-NU��K�2��C��a쌢�T.(�-������,K����� ȯ�      :   �  x�͔Oo�0���S��C�r5�i��4�v�[�]��,�컯T��`��\�/�˓�[nk^(���`���י��&|Y)kQ�� ��'!��L�ڵ���\5`.�?e��U�(A�!�!Dz����f��P�p�GV�a:�6��O7ʔ*�b�x�˂�6P�+ݳn�M�y��$���ْW�Zm�E��:z�,{+6�R�l^{�hQ��Xl�՘�WY���3�en��@02�MB]�F,�$��n�fv���j�����1y`�D�E��|/,����23��WL|B��j���$�ԭKܬ�O|&G�wr[::�����G̈́�O)�L�a��"�R�ZƓ뙤'��v�iP�߳��R�=�IsQ���$�'�C���?jI;1<l��|�<����\      @   �   x��ϱ
�0����%��KҢ���]\��@RH���n���=�?|�<T�m?(�C�ƭ2������|�6{�e��@z���]�L��w��r��k$�/�F�аph���D��d9�����k�0衘?zc1��٣�����R)�[p��      <   j   x�3�4�4�tN,J�42�30 �Fƺ&����n�E
�e�y%
!E��\F�F`�����ԂL�2*1�tJ��Ʈb�^*�	�)�	�jC���AT�4F��� @-      ?   +   x�ILW��K�
�!��:�("�_Zad��r��qqq ��      8   Y  x���Is�@��ͯP�l�6��P�	�O�t�ALy4M�F�q*�=#��d�mN�롛�U�se���4C.�c�65D������붝 ��1�V��`����z�	c������T�=�1�?Y��쑣}f�C��[���Q�Z1�+��5%�\K~�Tn�Z�����=�~�z��fOs��E}�Ni��Q�f�x�o}c��B��hꬩP�b%���P|�J0!+_��/x\ndڊ�`*]�g���WYF�H�%g�Yr;�E�4R^��:�'"ѭ�uﮋ"�g�a�u�C~�c��8{t�G9?��HN�.2ɤ>�{H"9|�����%��&����ގ���ao�)S�v���K�P��`�q�ؖM�sc���9�~ p$��?�<_��0������/VtoX�qӸ�&��Y���0��Ԫ.ӡ��z7������k�����e*0a����>\r�1%�������7>��g�f��+L��%��{��^���SkRLQv�ؕ�
�w(�pG"�%	�jpt��5�U�Y=.�o�etVˊ�|E	ʆ���"���@޼�nyǃ����G�7)1����@�H�/k��D�=o�Z�i�      